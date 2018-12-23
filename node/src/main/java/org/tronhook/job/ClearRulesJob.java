package org.tronhook.job;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tronhook.TronHookNodeConfig;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@DisallowConcurrentExecution
@Singleton
public class ClearRulesJob {

	
	private Jongo jongo;
	private TronHookNodeConfig config;
	
	@Inject
	public ClearRulesJob(Jongo jongo,TronHookNodeConfig config) {
		this.jongo = jongo;
		this.config = config;
	}
	
	@Scheduled("30m")
	public void removeAllRules() {
		
		if (!this.config.clearRules()) {
			return;
		}
		
		MongoCollection rulesCollection = this.jongo.getCollection("rules");
		
		rulesCollection.remove("{}");
		
	}
	
}
