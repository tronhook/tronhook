package org.tronhook.hook;

import java.util.List;

import org.tronhook.api.TronTransactionHook;
import org.tronhook.api.model.TransactionModel;

import com.google.inject.Inject;
import com.typesafe.config.Config;

public class AccountSyncElasticHook extends TronTransactionHook{

	@Inject
	public AccountSyncElasticHook(Config config) {
		super(config);
	}


	@Override
	public void processTransactions(List<TransactionModel> transactions) {
		
		for(TransactionModel tx:transactions) {
			System.out.println("tx=>"+tx.getHash()+" type:"+tx.getType() + " from:"+tx.getFrom());
		}
		
	}


}
