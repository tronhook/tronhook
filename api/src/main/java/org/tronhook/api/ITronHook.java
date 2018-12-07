package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

public interface ITronHook {

	
	public void processBlocks(List<BlockModel> block) throws TronHookException;
	
	public void processTransactions(List<TransactionModel> transactions) throws TronHookException;
	
	
	
}
