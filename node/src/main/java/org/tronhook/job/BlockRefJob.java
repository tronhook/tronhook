package org.tronhook.job;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tronhook.model.BlockRef;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;

import io.trxplorer.troncli.TronFullNodeCli;

@DisallowConcurrentExecution
@Singleton
public class BlockRefJob {

	private Jongo jongo;
	private TronFullNodeCli fullCli;
	
	@Inject
	public BlockRefJob(Jongo jongo,TronFullNodeCli fullCli) {
		this.jongo = jongo;
		this.fullCli = fullCli;
	}
	
	@Scheduled("1s")
	public void addBlockRefs() {
		
		MongoCollection blocks = this.jongo.getCollection("blocks");
		
		long lastBlockNum = this.fullCli.getLastBlock().getBlockHeader().getRawData().getNumber();
		
		BlockRef lastBlock = blocks.findOne().orderBy("{_id: -1}").as(BlockRef.class);
		
		long lastRefBlock = lastBlock==null ? 0 : Long.valueOf(lastBlock.getId());




		System.out.println("Mongo last block=>"+lastRefBlock);
		System.out.println("Tron last block ===>"+lastBlockNum);

		int bulkSize = 0;

		BulkWriteOperation bulk = blocks.getDBCollection().initializeOrderedBulkOperation();
		
		for(long i=lastRefBlock+1;i<=lastBlockNum;i++) {
			
			BasicDBObject newDoc = new BasicDBObject("_id", i);
			newDoc.append("tries", 0);
			newDoc.append("processed", 0);
			bulk.insert(newDoc);
			
			
			if (bulkSize==100000) {				
				bulk.execute();
				bulkSize = 0;
				break;
			}
			
			bulkSize++;
		}
		
		if (bulkSize>0) {
			bulk.execute();
		}
		

		


	}
	
}
