package org.tronhook.service;

import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.tron.protos.Protocol.Block;
import org.tronhook.api.ITronHook;
import org.tronhook.api.TronBlockHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.TronTransactionHook;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;
import org.tronhook.api.parser.BlockParser;
import org.tronhook.api.parser.BlockParserException;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

public class BlockProcessorService {

	private ITronHook hook;
	private Jongo jongo;
	
	@Inject
	public BlockProcessorService(ITronHook hook,Jongo jongo) {
		this.hook = hook;
		this.jongo = jongo;
	}
	
	
	public void processBlocks(List<Block> blocks) {
		
		try {
			
			List<BlockModel> parsedBlocks = BlockParser.parseBlocks(blocks);
			
			MongoCollection blocksCollection = this.jongo.getCollection("blocks");

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
			
			//block hook
			if (hook instanceof TronBlockHook) {
				
				TronBlockHook blockHook = (TronBlockHook) hook;
				blockHook.processBlocks(parsedBlocks);	
			}
			
			//tx hook
			if (hook instanceof TronTransactionHook) {
				
				TronTransactionHook txHook = (TronTransactionHook) hook;

				List<TransactionModel> allTransactions = new ArrayList<>();
				
				for(BlockModel b:parsedBlocks) {
					allTransactions.addAll(b.getTransactions());
				}

				txHook.processTransactions(allTransactions);
			}
			

			
		} catch (BlockParserException | TronHookException e) {

			e.printStackTrace();
		}
		

		
	}
	
}
