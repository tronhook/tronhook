package org.tronhook.api;

import java.util.ArrayList;
import java.util.List;

public class BlockInfo {

	private Long lastFullBlock;
	
	private Long lastSolidityBlock;

	private List<Long> unprocessedBlocks;
	
	private List<Long> removeTry;
	
	public BlockInfo() {
		this.unprocessedBlocks = new ArrayList<>();
		this.removeTry = new ArrayList<>();
	}
	
	public Long getLastFullBlock() {
		return lastFullBlock;
	}

	public void setLastFullBlock(Long getLastFullBlock) {
		this.lastFullBlock = getLastFullBlock;
	}

	public Long getLastSolidityBlock() {
		return lastSolidityBlock;
	}

	public void setLastSolidityBlock(Long getLastSolidityBlock) {
		this.lastSolidityBlock = getLastSolidityBlock;
	}
	
	public void addUnprocessedBlock(Long num) {
		this.unprocessedBlocks.add(num);
	}
	
	public void addRemoveTry(Long num) {
		this.removeTry.add(num);
	}
	
}
