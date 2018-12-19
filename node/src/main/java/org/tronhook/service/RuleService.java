package org.tronhook.service;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.tronhook.api.model.Rule;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RuleService {

	private Jongo jongo;
	
	@Inject
	public RuleService(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public void addOrUpdateRule(Rule rule) {
		
		MongoCollection rulesCollection = this.jongo.getCollection("rules");
		
		
		rulesCollection.save(rule);
		
	}
	
}
