package org.tronhook.api;

import java.util.ArrayList;
import java.util.List;

public class BlockInfo {

	private Long lastFullBlock;
	
	private Long lastSolidityBlock;

	
	public BlockInfo() {
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
	

	
}
