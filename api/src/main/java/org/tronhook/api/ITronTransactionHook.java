package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.TransactionModel;

public interface ITronTransactionHook extends ITronHook{

	public void processTransactions(List<TransactionModel> transactions) throws TronHookException;
	
}
