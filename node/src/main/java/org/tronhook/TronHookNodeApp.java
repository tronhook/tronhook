package org.tronhook;

import org.jongo.Jongo;
import org.jooby.Jooby;
import org.jooby.mongodb.Jongoby;
import org.jooby.mongodb.Mongodb;
import org.jooby.quartz.Quartz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tronhook.job.BlockRefJob;
import org.tronhook.job.LastBlockCache;
import org.tronhook.job.LastestBlockProcessorJob;
import org.tronhook.job.PreviousBlockProcessorJob;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;


public class TronHookNodeApp extends Jooby {
	
	private static final Logger logger = LoggerFactory.getLogger(TronHookNodeApp.class);
	
	{
		use(new Mongodb());
		use(new Jongoby());
		use(new TronHookNodeModule());
	
		
		use(new Quartz(BlockRefJob.class,
				LastestBlockProcessorJob.class,
				LastBlockCache.class
				//UnprocessedBlockProcessorJob.class
				));
		
		
		

		
		onStart((registry)->{

			Jongo jongo = registry.require(Jongo.class);
			
			TronHookNodeConfig config = registry.require(TronHookNodeConfig.class);

			jongo.getCollection(Helper.getBlockCollectionName(config)).ensureIndex("{tries:1}");
			jongo.getCollection(Helper.getBlockCollectionName(config)).ensureIndex("{processed:1}","{background:true}");
		});
		
		onStop((registry)->{

			TronFullNodeCli fullCli = registry.require(TronFullNodeCli.class);
			fullCli.shutdown();
			
			TronSolidityNodeCli solidityCli = registry.require(TronSolidityNodeCli.class);
			solidityCli.shutdown();

			
		});
		

		get("/rules", (req, res) -> {

			res.send("");
		});
		
		post("/rule", (req, res) -> {

			res.send("");
		});

	}

	public static void main(final String[] args) {
		run(TronHookNodeApp::new, args);
	}

}
