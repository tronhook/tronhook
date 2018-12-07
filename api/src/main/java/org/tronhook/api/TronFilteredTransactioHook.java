package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

public abstract class TronFilteredTransactioHook extends TronHook{

	public TronFilteredTransactioHook(Config config) {
		super(config);
	}

	public abstract void processFilteredTransactions(List<TransactionModel> transactions) throws TronHookException;



}
