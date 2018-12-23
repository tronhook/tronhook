package org.tronhook.model;

public class StatsModel {

	public long lastProcessedBlock;

	public int totalRules;
	
	public long blockOnError;
	
	public long unprocessedBlocks;
	
	public long getLastProcessedBlock() {
		return lastProcessedBlock;
	}

	public void setLastProcessedBlock(long lastProcessedBlock) {
		this.lastProcessedBlock = lastProcessedBlock;
	}

	public int getTotalRules() {
		return totalRules;
	}

	public void setTotalRules(int totalRules) {
		this.totalRules = totalRules;
	}

	public long getBlockOnError() {
		return blockOnError;
	}

	public void setBlockOnError(long blockOnError) {
		this.blockOnError = blockOnError;
	}

	public long getUnprocessedBlocks() {
		return unprocessedBlocks;
	}

	public void setUnprocessedBlocks(long unprocessedBlocks) {
		this.unprocessedBlocks = unprocessedBlocks;
	}
	
	
	
}
