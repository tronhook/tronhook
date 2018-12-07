package org.tronhook.hook;

import java.util.List;

import org.tron.protos.Protocol.Block;
import org.tron.protos.Protocol.Transaction;
import org.tronhook.api.ITronHook;
import org.tronhook.api.TronHook;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

public class AccountSyncElasticHook extends TronHook{

	public AccountSyncElasticHook(Config config) {
		super(config);
	}

	@Override
	public void processBlocks(List<BlockModel> block) {
		
		for(BlockModel b:block) {
			System.out.println("=>"+b.getHeight()+" = >"+b.getWitness());
		}
		
	}

	@Override
	public void processTransactions(List<TransactionModel> transactions) {
		// TODO Auto-generated method stub
		
	}


}
