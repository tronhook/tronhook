package org.tronhook.job;

import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.quartz.Scheduled;
import org.tronhook.api.model.Rule;
import org.tronhook.service.BlockProcessorService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RulesFetcherJob {

	private Jongo jongo;
	private BlockProcessorService bpService;
	
	@Inject
	public RulesFetcherJob(Jongo jongo,BlockProcessorService bpService) {
		this.jongo = jongo;
		this.bpService = bpService;
	}
	
	@Scheduled("5s")
	public void fetchRules() {
		
		MongoCursor<Rule> blockRulesCursor = this.jongo.getCollection("rules").find("{context:'block',valid:true}").as(Rule.class);
		MongoCursor<Rule> txRulesCursor = this.jongo.getCollection("rules").find("{context:'transaction',valid:true}").as(Rule.class);
		
		List<Rule> blockRules = new ArrayList<>();

		List<Rule> txRules = new ArrayList<>();
		
		blockRulesCursor.forEach((bR)->{
			
			blockRules.add(bR);
			
		});
		
		txRulesCursor.forEach((tx)->{
			
			txRules.add(tx);
		});
		
		bpService.setBlockRules(blockRules);
		bpService.setTxRules(txRules);
		
		
		
	}
	
	
}
