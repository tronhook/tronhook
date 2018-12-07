package org.tronhook.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tron.protos.Protocol.Block;

public abstract class AbstractBlockProcessorJob {
	
	
	public void processBatch(int maxBatchSize,int workerBatchSize,int workers,long timemout) {
		
		List<Block> txs = getBlocks(maxBatchSize);

		for (int i = 0; i < txs.size(); i++) {
			
			ArrayList<CompletableFuture> futures = new ArrayList<>();
			
			for(int w=0; w < workers;w++) {
				
				int workerStart = i;
				int workerStop = i + workerBatchSize;
				
				if (workerStop>=txs.size()) {
					workerStop = txs.size();
				}
				
				List<Block> workerTx = txs.subList(workerStart, workerStop);
				
				getLogger().info("- {} worker {} processing batch: [{}-{}]",getName(),w,workerStart,workerStop);
				futures.add(processWorkerBatch(workerTx));
				
				i = workerStop;
				if (i==txs.size()) {
					break;
				}
			}
			
			
			try {
				getLogger().info("=> Wating for {} batch to complete: [{}-{}] ...",getName());
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
				getLogger().info("=> Finished {} batch to complete: [{}-{}] ...",getName());
			} catch (InterruptedException | ExecutionException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	protected abstract List<Block> getBlocks(int maxBatchSize);
	
	public abstract CompletableFuture<Void> processWorkerBatch(List<Block> workerBlocks);
	
	public String getName() {
		return this.getClass().getName();
	}
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
	
	
}
