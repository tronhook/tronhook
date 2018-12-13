package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.TransactionModel;

public interface ITronFilteredTransactionHook extends ITronHook{

	public abstract void processFilteredTransactions(List<TransactionModel> transactions) throws TronHookException;
	
}
