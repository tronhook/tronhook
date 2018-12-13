package org.tronhook.job;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tronhook.TronHookNodeConfig;
import org.tronhook.model.BlockRef;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

import io.trxplorer.troncli.TronFullNodeCli;

@DisallowConcurrentExecution
@Singleton
public class BlockRefJob {

	private Jongo jongo;
	private TronFullNodeCli fullCli;
	private TronHookNodeConfig config;
	
	
	@Inject
	public BlockRefJob(Jongo jongo,TronFullNodeCli fullCli,TronHookNodeConfig config) {
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.config = config;
	}
	
	@Scheduled("1s")
	public void addBlockRefs() {
		
		MongoCollection blocks = this.jongo.getCollection((this.config.getHookId()+"_"+this.config.getNodeId()+"_blocks").toLowerCase());
		
		long lastBlockNum = this.fullCli.getLastBlock().getBlockHeader().getRawData().getNumber();
		
		BlockRef lastBlock = blocks.findOne().orderBy("{_id: -1}").as(BlockRef.class);
		
		long lastRefBlock = lastBlock==null ? 0 : Long.valueOf(lastBlock.getId());

		int batchSize = 0;

		BulkWriteOperation bulk = blocks.getDBCollection().initializeOrderedBulkOperation();
		
		for(long i=lastRefBlock+1;i<=lastBlockNum;i++) {
			
			BasicDBObject newDoc = new BasicDBObject("_id", i);
			newDoc.append("tries", 0);
			newDoc.append("processed", 0);
			bulk.insert(newDoc);
			
			
			if (batchSize==100000) {				
				bulk.execute();
				batchSize = 0;
				break;
			}
			
			batchSize++;
		}
		
		if (batchSize>0) {
			bulk.execute();
		}
		

		


	}
	
}
