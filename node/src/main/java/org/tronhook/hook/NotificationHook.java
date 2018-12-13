package org.tronhook.hook;

import java.util.List;

import org.tronhook.api.ITronTransactionHook;
import org.tronhook.api.TronBlockHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

public class NotificationHook extends TronBlockHook implements ITronTransactionHook{

	public NotificationHook(Config config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onNodeStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNodeStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processTransactions(List<TransactionModel> transactions) throws TronHookException {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
