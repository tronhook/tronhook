package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

public abstract class TronTransactionHook extends TronHook{

	public TronTransactionHook(Config config) {
		super(config);
	}

	public abstract void processTransactions(List<TransactionModel> transactions) throws TronHookException;
	


}
