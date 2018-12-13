package org.tronhook.job;

import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;

@DisallowConcurrentExecution
@Singleton
public class LastBlockCache {
	
	private TronFullNodeCli fullCli;
	private TronSolidityNodeCli solidityCli;
	private long lastBlockFull;
	private long lastBlockSolidity;
	
	@Inject
	public LastBlockCache(TronFullNodeCli fullCli,TronSolidityNodeCli solidityCli) {
		this.fullCli = fullCli;
		this.solidityCli = solidityCli;
	}
	
	@Scheduled("3s")
	public void getLastBlocks() {
		
		this.lastBlockFull = this.fullCli.getLastBlock().getBlockHeader().getRawData().getNumber();
		this.lastBlockSolidity = this.solidityCli.getLastBlock().getBlockHeader().getRawData().getNumber();
		
	}
	
	
	public long getLastBlockFull() {
		return lastBlockFull;
	}
	
	public long getLastBlockSolidity() {
		return lastBlockSolidity;
	}
	
}
