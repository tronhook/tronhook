package org.tronhook.api.model.core;

import java.util.List;

public class ItemsModel {
	
	private ItemTypeModel type;
	
	private List<Block> blocks;
	
	private List<BlockHeaderModel> blockHeaders;
	
	private List<Transaction> transactions;

	public ItemTypeModel getType() {
		return type;
	}

	public void setType(ItemTypeModel type) {
		this.type = type;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public List<BlockHeaderModel> getBlockHeaders() {
		return blockHeaders;
	}

	public void setBlockHeaders(List<BlockHeaderModel> blockHeaders) {
		this.blockHeaders = blockHeaders;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
