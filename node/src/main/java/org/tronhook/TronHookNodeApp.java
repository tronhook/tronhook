package org.tronhook;


import org.apache.commons.collections4.map.HashedMap;
import org.jongo.Jongo;
import org.jooby.Jooby;
import org.jooby.Status;
import org.jooby.handlers.CorsHandler;
import org.jooby.json.Jackson;
import org.jooby.mongodb.Jongoby;
import org.jooby.mongodb.Mongodb;
import org.jooby.quartz.Quartz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tronhook.api.model.Rule;
import org.tronhook.job.BlockRefJob;
import org.tronhook.job.ClearRulesJob;
import org.tronhook.job.LastBlockCache;
import org.tronhook.job.LastestBlockProcessorJob;
import org.tronhook.job.PreviousBlockProcessorJob;
import org.tronhook.job.RulesFetcherJob;
import org.tronhook.model.StatsModel;
import org.tronhook.service.BlockProcessorService;
import org.tronhook.service.RuleService;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;


public class TronHookNodeApp extends Jooby {
	
	private static final Logger logger = LoggerFactory.getLogger(TronHookNodeApp.class);
	
	{
		use(new Mongodb());
		use(new Jongoby());
		use(new TronHookNodeModule());
		use(new Jackson());
		use("*", new CorsHandler());
		
		use(new Quartz(BlockRefJob.class,
				LastestBlockProcessorJob.class,
				PreviousBlockProcessorJob.class,
				LastBlockCache.class,
				RulesFetcherJob.class,
				ClearRulesJob.class
				));
		
		

		
		onStart((registry)->{

			Jongo jongo = registry.require(Jongo.class);
			
			TronHookNodeConfig config = registry.require(TronHookNodeConfig.class);
			//ensure mongo indexes
			jongo.getCollection(Helper.getBlockCollectionName(config)).ensureIndex("{tries:1}","{background:true}");
			jongo.getCollection(Helper.getBlockCollectionName(config)).ensureIndex("{processed:1}","{background:true}");
			jongo.getCollection(Helper.getBlockCollectionName(config)).ensureIndex("{tries:1,processed:1}","{background:true}");
			jongo.getCollection("rules").ensureIndex("{id:1}","{background:true}");
		});
		
		onStop((registry)->{

			TronFullNodeCli fullCli = registry.require(TronFullNodeCli.class);
			fullCli.shutdown();
			
			TronSolidityNodeCli solidityCli = registry.require(TronSolidityNodeCli.class);
			solidityCli.shutdown();

			
		});
		
		
		get("/stats", (req, res) -> {
			
			StatsModel stats  = new StatsModel();
			
			BlockProcessorService bpService = req.require(BlockProcessorService.class);
			
			stats.setLastProcessedBlock(bpService.getLastProcessedBlock());

			res.send(stats);
		});
		
		get("/rules", (req, res) -> {

			RuleService service = req.require(RuleService.class);

			res.send(service.getRules());
		});
		
		get("/rule/:id", (req, res) -> {
			
			String id = req.param("id").value();
			
			RuleService service = req.require(RuleService.class);
			
			Rule rule = service.getRuleById(id);
			
			if (rule!=null) {
				res.send(rule);	
			}else {
				//send empty result
				res.send(new HashedMap<>());
			}
			
		});
		
		delete("/rule/:id", (req, res) -> {
			
			String id = req.param("id").value();
			
			RuleService service = req.require(RuleService.class);
			
			boolean removed = service.getRemoveRuleById(id);
			
			if (removed) {
				res.send("ok");	
			}else {
				res.status(Status.NOT_FOUND);
				res.send("Rule doesn't exists");
			}
			
		});
		
		post("/rule", (req, res) -> {
			
			Rule rule = req.body(Rule.class);
			
			RuleService service = req.require(RuleService.class);
			
			service.addOrUpdateRule(rule);
			
			res.send(rule);
		});
		
		post("/test", (req, res) -> {
			System.out.println(req.body().value());
			
			
			
			res.send(true);
		});

	}

	public static void main(final String[] args) {
		run(TronHookNodeApp::new, args);
	}

}
