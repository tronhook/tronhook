package org.tronhook.api.model.core;

import java.util.List;

public class Block {
	
	private List<Transaction> transactions;
	
	private BlockHeaderModel blockHeader;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public BlockHeaderModel getBlockHeader() {
		return blockHeader;
	}

	public void setBlockHeader(BlockHeaderModel blockHeader) {
		this.blockHeader = blockHeader;
	}

}
