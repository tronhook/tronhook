package org.tronhook.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.tron.protos.Protocol.Block;
import org.tronhook.Helper;
import org.tronhook.TronHookNodeConfig;
import org.tronhook.api.BlockInfo;
import org.tronhook.api.ITronHook;
import org.tronhook.api.NodeType;
import org.tronhook.api.TronHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.Rule;
import org.tronhook.api.model.TransactionModel;
import org.tronhook.api.parser.BlockParser;
import org.tronhook.api.parser.BlockParserException;
import org.tronhook.job.LastBlockCache;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;

@Singleton
public class BlockProcessorService {

	private ITronHook hook;
	private Jongo jongo;
	private TronFullNodeCli fullCli;
	private LastBlockCache lbCache;
	private TronHookNodeConfig config;
	private TronSolidityNodeCli solidityCli;
	
	private static final String FULL_NODE_TYPE = "full";
	private static final String SOLIDITY_NODE_TYPE = "solidity";
	
	private List<Rule> txRules = new ArrayList<>();
	private List<Rule> blockRules = new ArrayList<>();
	private SpelExpressionParser spelParser;
	
	@Inject
	public BlockProcessorService(ITronHook hook,Jongo jongo,TronFullNodeCli fullCli,TronSolidityNodeCli solidityCli,LastBlockCache lbCache,TronHookNodeConfig config) {
		this.hook = hook;
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.solidityCli = solidityCli;
		this.lbCache = lbCache;
		this.config = config;
		
		SpelParserConfiguration spelConfig = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
	    BlockProcessorService.class.getClassLoader());

		this.spelParser = new SpelExpressionParser(spelConfig);

	}
	
	
	public synchronized void processBlocks(List<Long> blocksNums) {
		
		try {
			
			getLogger().info("Processing blocks: {}",blocksNums);
			
			List<Block> blocks = Collections.emptyList(); 
			
			NodeType nodeType = NodeType.FULL;
			if (this.config.getNodeType().toLowerCase().equals(SOLIDITY_NODE_TYPE)) {
				blocks = this.solidityCli.getBlocksByNums(blocksNums);
				nodeType = nodeType.SOLIDITY;
			}else {
				blocks = this.fullCli.getBlockByNums(blocksNums);
			}
				
			
			List<BlockModel> parsedBlocks = BlockParser.parseBlocks(blocks);
			
			if (parsedBlocks.size()==0) {
				return;
			}
			
			Map<String,List<Object>> matchingRules = new HashMap<>();
			
			
			//filter blocks with block rule context
			List<BlockModel> filteredBlocks = getFilteredBlocks(parsedBlocks, matchingRules);
			
			
			
			MongoCollection blocksCollection = this.jongo.getCollection(Helper.getBlockCollectionName(config));

			//first increment try on blocks
			BulkWriteOperation incrementTrybulk = blocksCollection.getDBCollection().initializeOrderedBulkOperation();			
			for(BlockModel block:parsedBlocks) {
				incrementTrybulk.find(new BasicDBObject("_id", block.getHeight())).updateOne(BasicDBObject.parse("{'$inc':{'tries':1}}"));
			}
			

			incrementTrybulk.execute();
				
			//then set processed blocks
			BulkWriteOperation processedBulk = blocksCollection.getDBCollection().initializeOrderedBulkOperation();
			
			for(BlockModel block:parsedBlocks) {
				processedBulk.find(new BasicDBObject("_id", block.getHeight())).updateOne((BasicDBObject.parse("{'$set':{'processed':1}}")));
			}	
			
			processedBulk.execute();
			
			if (hook instanceof TronHook) {
				TronHook thook = (TronHook) hook;
				BlockInfo bi = new BlockInfo();
				bi.setLastFullBlock(lbCache.getLastBlockFull());
				bi.setLastSolidityBlock(lbCache.getLastBlockSolidity());
				thook.setNodeType(nodeType);
				thook.setBlockInfo(bi);
				thook.setMatchingRules(matchingRules);
				
				thook.processBlocks(filteredBlocks);

			}

		} catch (BlockParserException | TronHookException e) {

			e.printStackTrace();
		}
		

		
	}
	
	private List<BlockModel> getFilteredBlocks(List<BlockModel> parsedBlocks,Map<String, List<Object>> matchingRules) {
		 
	
		ArrayList<BlockModel> result = new ArrayList<>();
		
	
		
		
		//filter blocks
		for(BlockModel block:parsedBlocks) {
			
			for(Rule rule:blockRules) {
				
				Expression expr = (Expression) spelParser.parseExpression(rule.getRule());
				EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().withRootObject(block).build();
				
				boolean ruleMatch = expr.getValue(context,Boolean.class);
				
				if (ruleMatch) {
					result.add(block);
					addRule(rule.getId(),block, matchingRules);
				}
				
			}
			
		}
		
		//if not block rules => take them all
		if (blockRules.size()==0) {
			result.addAll(parsedBlocks);
		}
		
		
		//filter transactions
		for(BlockModel filteredBlock:result) {
			
			Iterator<TransactionModel> it = filteredBlock.getTransactions().iterator();
			
			while(it.hasNext()) {
				
				TransactionModel tx = it.next();
				
				boolean hasMatchingRule = false;
				
				for(Rule rule:txRules) {
					
					Expression expr = (Expression) spelParser.parseExpression(rule.getRule());
					EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().withRootObject(tx).build();
					
					boolean ruleMatch = expr.getValue(context,Boolean.class);
					
					if (ruleMatch) {
						addRule(rule.getId(),tx, matchingRules);
						hasMatchingRule = true;
					}
					
				}
				
				//remove tx matching no rules at all 
				if (txRules.size()>0 && hasMatchingRule==false) {
					it.remove();
				}
				
			}
			

			
		}
		
		return result;
		

	}
	
	private void addRule(String ruleId,Object fact,Map<String, List<Object>> matchingRules) {
		
		List<Object> facts = matchingRules.get(ruleId);
		
		if (facts==null) {
			facts = new ArrayList<>();
		}
		
		facts.add(fact);
		
		matchingRules.put(ruleId, facts);
	}
	
	public void setBlockRules(List<Rule> blockRules) {
		this.blockRules = blockRules;
	}
	
	public void setTxRules(List<Rule> txRules) {
		this.txRules = txRules;
	}
	

	
	public Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
	
}
