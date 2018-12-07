package org.tronhook.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.tron.protos.Protocol.Block;
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
	
	@Inject
	public LastBlockProcessorJob(Jongo jongo,TronFullNodeCli fullCli,BlockProcessorService processor) {
		this.jongo = jongo;
		this.fullCli = fullCli;
		this.processor = processor;
	}

	@Scheduled("1s")
	public void processBlocks() {
		this.processBatch(10, 10, 1, 0);
	}
	
	@Override
	protected List<Block> getBlocks(int maxBatchSize) {
		
		MongoCursor<BlockRef> blocks = this.jongo.getCollection("blocks").find("{tries:{ $gte :  0, $lt : 3}}").sort("{_id:-1}").limit(10).as(BlockRef.class);
		
		List<Long> result= new ArrayList<>();
		
		while(blocks.hasNext()) {
			
			result.add(blocks.next().getNumber());
			
		}
		
		List<Block> bslocks = this.fullCli.getBlockByNums(result);
		
		return bslocks;
	}

	@Override
	public synchronized CompletableFuture<Void> processWorkerBatch(List<Block> workerBlocks) {

		return CompletableFuture.runAsync(()->{
			
			processor.processBlocks(workerBlocks);	
			
		});
	}
	
	
	
	
}
