package org.tronhook.service;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import org.tronhook.api.parser.BlockParser;
import org.tronhook.api.parser.BlockParserException;
import org.tronhook.job.LastBlockCache;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;

public class BlockProcessorService {

	private ITronHook hook;
	private Jongo jongo;
	private TronFullNodeCli fullCli;
	private LastBlockCache lbCache;
	private TronHookNodeConfig config;
	private TronSolidityNodeCli solidityCli;
	
	private static final String FULL_NODE_TYPE = "full";
	private static final String SOLIDITY_NODE_TYPE = "solidity";
	
	@Inject
	public BlockProcessorService(ITronHook hook,Jongo jongo,TronFullNodeCli fullCli,TronSolidityNodeCli solidityCli,LastBlockCache lbCache,TronHookNodeConfig config) {
		this.hook = hook;
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.solidityCli = solidityCli;
		this.lbCache = lbCache;
		this.config = config;
	}
	
	
	public void processBlocks(List<Long> blocksNums) {
		
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
				thook.processBlocks(parsedBlocks);
			}
			
		
			

			
		} catch (BlockParserException | TronHookException e) {

			e.printStackTrace();
		}
		

		
	}
	
	public static void main(String[] args) throws BlockParserException {
		
		TronSolidityNodeCli cli = new TronSolidityNodeCli("127.0.0.1:50051", true);
		
		BlockParser.parseBlocks(cli.getBlocksByNums(Arrays.asList(5008683l, 5008682l, 5008681l, 5008680l, 5008679l)));

		
//		SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
//			    BlockProcessorService.class.getClassLoader());
//
//			SpelExpressionParser parser = new SpelExpressionParser(config);
//
//			Expression expr = (Expression) parser.parseExpression("hash=='123'");
//
//			BlockModel bm = new BlockModel();
//			bm.setHash("123");
//			
//			EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().withRootObject(bm).build();
//
//			boolean rule = expr.getValue(context,Boolean.class);
//		
			
			
	}
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
	
}
