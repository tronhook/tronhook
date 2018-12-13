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
public class LastBlockProcessorJob extends AbstractBlockProcessorJob{

	
	private Jongo jongo;
	private TronFullNodeCli fullCli;
	private BlockProcessorService processor;
	private TronHookNodeConfig config;
	
	@Inject
	public LastBlockProcessorJob(Jongo jongo,TronFullNodeCli fullCli,BlockProcessorService processor,TronHookNodeConfig config) {
		super(config);
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.processor = processor;
		this.config = config;
	}

	@Scheduled("200ms")
	public void processBlocks() {
		
		//does not run if last block !=-1
		if (this.config.getBlockStop()!=-1) {
			return;
		}
		
		this.processBatch(10, 10, 1, 0);
	}
	
	@Override
	protected List<Long> getBlocks(int maxBatchSize) {
		
		MongoCursor<BlockRef> blocks = this.jongo.getCollection(getBlocksCollectionName()).find("{tries:{ $gte :  0, $lt : 3},processed:0}").sort("{_id:-1}").limit(maxBatchSize).as(BlockRef.class);
		
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
