package org.tronhook.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.tronhook.api.model.Rule;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.WriteResult;

@Singleton
public class RuleService {

	private Jongo jongo;
	private MongoCollection rulesCollection;
	
	@Inject
	public RuleService(Jongo jongo) {
		this.jongo = jongo;
		this.rulesCollection = this.jongo.getCollection("rules");
	}
	
	public void addOrUpdateRule(Rule rule) {

		rulesCollection.save(rule);
		
	}
	
	public Rule getRuleById(String id) {
		
		return this.rulesCollection.findOne("{id:#}",id).as(Rule.class);
		
	}
	

	public List<Rule> getInvalidRules(){
		return this.getRules("{valid:false}");
	}
	
	public List<Rule> getRules(){
		return this.getRules("{}");
	}
	
	public List<Rule> getRules(String query){
		
		MongoCursor<Rule> res = this.rulesCollection.find(query).as(Rule.class);
		
		Iterator<Rule> it = res.iterator();
		
		ArrayList<Rule> rules = new ArrayList<>();
		
		while(it.hasNext()) {
			rules.add(it.next());
		}
		
		return rules;
	}
	
	public boolean removeRuleById(String id) {
		
		WriteResult result = this.rulesCollection.remove("{id:#}",id);
		
		if (result.getN()==1) {
			return true;
		}
		
		return false;
	}
	
	public void invalidateRule(String id,String error) {
		this.rulesCollection.update("{id:#}",id).with("{$set:{valid:false,error: #}}",error);
	}
	
}
