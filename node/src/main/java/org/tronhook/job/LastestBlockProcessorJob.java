package org.tronhook.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tronhook.TronHookNodeConfig;
import org.tronhook.model.BlockRef;
import org.tronhook.service.BlockProcessorService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import io.trxplorer.troncli.TronFullNodeCli;

@DisallowConcurrentExecution
@Singleton
public class LastestBlockProcessorJob extends AbstractBlockProcessorJob{

	
	private Jongo jongo;
	private TronFullNodeCli fullCli;
	private BlockProcessorService processor;
	private TronHookNodeConfig config;
	private LastBlockCache lastBlockCache;
	
	@Inject
	public LastestBlockProcessorJob(Jongo jongo,TronFullNodeCli fullCli,BlockProcessorService processor,TronHookNodeConfig config,LastBlockCache lastBlockCache) {
		super(config,lastBlockCache);
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.processor = processor;
		this.config = config;
	}

	@Scheduled("200ms")
	public void processBlocks() {
		
		if (!this.config.getLastestBlocksJobConfig().isElabled()) {
			return;
		}
		
		//does not run if last block !=-1
		if (this.config.getBlockStop()!=-1) {
			return;
		}
		
		int batchSize = this.config.getLastestBlocksJobConfig().getBatchSize();
		int workerBatchSize = this.config.getLastestBlocksJobConfig().getWorkerBatchSize();
		int workers = this.config.getLastestBlocksJobConfig().getWorkers();
		
		this.processBatch(batchSize, workerBatchSize, workers, 0);
	}
	
	@Override
	protected List<Long> getBlocks(int maxBatchSize) {
		
		MongoCursor<BlockRef> blocks = this.jongo.getCollection(getBlocksCollectionName()).find("{tries:{ $gte :  0, $lt : 3},processed:0,_id:{$gte :  #, $lte : #}}",this.config.getBlockStart(),this.getStopBlock()).sort("{_id:-1}").limit(maxBatchSize).as(BlockRef.class);
		
		List<Long> result= new ArrayList<>();
		
		while(blocks.hasNext()) {
			
			result.add(blocks.next().getNumber());
			
		}
		
		
		
		return result;
	}

	@Override
	public synchronized CompletableFuture<Void> processWorkerBatch(List<Long> workerBlocks) {

		return CompletableFuture.runAsync(()->{
			
			processor.processBlocks(workerBlocks);	
			
		});
	}
	
	
	
	
}
