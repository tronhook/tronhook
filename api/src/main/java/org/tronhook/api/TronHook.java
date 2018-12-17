package org.tronhook.api;

import java.util.ArrayList;
import java.util.List;

import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

public abstract class TronHook implements ITronHook{
	
	private Config config;
	
	private BlockInfo blockInfo;
	
	private NodeType nodeType;
	
	public TronHook(Config config) {
		this.config = config;
	}

	public abstract void processBlocks(List<BlockModel> blocks) throws TronHookException;

	protected Config getConfig() {
		return this.config;
	}

	public BlockInfo getBlockInfo() {
		return blockInfo;
	}


	public void setBlockInfo(BlockInfo blockInfo) {
		this.blockInfo = blockInfo;
	}


	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	
	public NodeType getNodeType() {
		return nodeType;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	protected List<TransactionModel> getAllTransactions(List<BlockModel> blocks) {
		
		List<TransactionModel> transactions = new ArrayList<>();
		
		
		for(BlockModel b:blocks) {
			transactions.addAll(b.getTransactions());
		}
		
		return transactions;
	}
	
	
}
