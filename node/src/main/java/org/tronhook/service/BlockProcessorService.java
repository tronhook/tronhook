package org.tronhook.service;

import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.tron.protos.Protocol.Block;
import org.tronhook.api.TronHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.parser.BlockParser;
import org.tronhook.api.parser.BlockParserException;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

public class BlockProcessorService {

	private TronHook hook;
	private Jongo jongo;
	
	@Inject
	public BlockProcessorService(TronHook hook,Jongo jongo) {
		this.hook = hook;
		this.jongo = jongo;
	}
	
	
	public void processBlocks(List<Block> blocks) {
		
		try {
			
			MongoCollection blocksCollection = this.jongo.getCollection("blocks");
			BulkWriteOperation bulk = blocksCollection.getDBCollection().initializeOrderedBulkOperation();
			
			
			for(Block block:blocks) {
				bulk.find(new BasicDBObject("_id", block.getBlockHeader().getRawData().getNumber())).updateOne(BasicDBObject.parse("{'$inc':{'tries':1}}"));
				
			}
			
			bulk.execute();
				
		
	
			
			List<BlockModel> parsedBlocks = BlockParser.parseBlocks(blocks);
			
			hook.processBlocks(parsedBlocks);
			
		} catch (BlockParserException | TronHookException e) {

			e.printStackTrace();
		}
		

		
	}
	
}
