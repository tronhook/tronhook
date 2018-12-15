package org.tronhook.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tronhook.TronHookNodeConfig;
import org.tronhook.model.BlockRef;
import org.tronhook.service.BlockProcessorService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@DisallowConcurrentExecution
@Singleton
public class PreviousBlockProcessorJob extends AbstractBlockProcessorJob{

	
	private Jongo jongo;
	private BlockProcessorService processor;
	private BlockingQueue<Long> unprocessedBlockPool;
	private TronHookNodeConfig config;
	
	@Inject
	public PreviousBlockProcessorJob(Jongo jongo,BlockProcessorService processor,TronHookNodeConfig config,LastBlockCache lastBlockCache) {
		super(config,lastBlockCache);
		this.jongo = jongo;
		this.processor = processor;
		this.unprocessedBlockPool = new LinkedBlockingDeque<>(100);
		this.config = config;
	}


	
	@Scheduled("200ms")
	public void processBlocks() {
		
		int batchSize = this.config.getPreviousBlocksJobConfig().getBatchSize();
		int workerBatchSize = this.config.getPreviousBlocksJobConfig().getWorkerBatchSize();
		int workers = this.config.getPreviousBlocksJobConfig().getWorkers();
		
		this.processBatch(batchSize, workerBatchSize,workers, 0);

	}
	
//	@Scheduled("200ms")
//	public void processBlocks2() {
//		this.processBatch(1000, 100, 10, 0);
//
//	}
//	
	
//	@Scheduled("1s")
//	public void populateQueue() throws InterruptedException {
//
//		
//		if (unprocessedBlockPool.size()>0) {
//			return;
//		}
//
//
//		
//		MongoCursor<BlockRef> blocks = this.jongo.getCollection(getBlocksCollectionName()).find("{tries:{ $gte :  0, $lt : 3},processed:0,_id:{$gte :  #, $lte : #,$nin:#}}",this.config.getBlockStart(),this.getStopBlock(),this.unprocessedBlockPool).sort("{_id:1}").limit(10000).as(BlockRef.class);
//
//		List<Long> result= new ArrayList<>();
//		
//		while(blocks.hasNext()) {
//			
//			unprocessedBlockPool.put(blocks.next().getNumber());
//			
//		}		
//		
//	}
	
	
	@Override
	protected List<Long> getBlocks(int maxBatchSize) {
		
		
		
		MongoCursor<BlockRef> blocks = this.jongo.getCollection(getBlocksCollectionName()).find("{tries:{ $gte :  0, $lt : 3},processed:0,_id:{$gte :  #, $lte : #}}",this.config.getBlockStart(),this.getStopBlock()).sort("{_id:1}").limit(maxBatchSize).as(BlockRef.class);

		List<Long> result= new ArrayList<>();
		
		while(blocks.hasNext()) {
			
			result.add(blocks.next().getNumber());
			
		}
	
//		List<Long> result= new ArrayList<>();
//		
//		unprocessedBlockPool.drainTo(result, 1000);
		
		
		return result;
	}

	@Override
	public synchronized CompletableFuture<Void> processWorkerBatch(List<Long> workerBlocks) {

		return CompletableFuture.runAsync(()->{
			
			processor.processBlocks(workerBlocks);	
			
		});
	}
	

	
//	public static void main(String[] args) throws IOException {
//		
//		
//		ExecutorService e = Executors.newFixedThreadPool(2);
//		
//		e.submit(()->{
//			
//			System.out.println("");
//			
//		});
//		
//	}
	
	
}
