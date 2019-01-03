package org.tronhook.api.parser;


import java.util.ArrayList;

import org.tron.core.Wallet;
import org.tron.core.services.http.JsonFormat;
import org.tron.protos.Contract.ExchangeTransactionContract;
import org.tron.protos.Contract.TransferAssetContract;
import org.tron.protos.Contract.TransferContract;
import org.tron.protos.Contract.TriggerSmartContract;
import org.tron.protos.Contract.VoteWitnessContract;
import org.tron.protos.Contract.VoteWitnessContract.Vote;
import org.tron.protos.Protocol.Transaction.Contract;
import org.tronhook.api.model.contract.ExchangeTransactionContractModel;
import org.tronhook.api.model.contract.TransferAssetContractModel;
import org.tronhook.api.model.contract.TransferContractModel;
import org.tronhook.api.model.contract.TriggerSmartContractModel;
import org.tronhook.api.model.contract.VoteWitnessContractModel;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class ContractParser {

	
	public static Object parse(Contract contract) throws InvalidProtocolBufferException {
	
		ByteString contractByteString = contract.getParameter().getValue();
		
		switch (contract.getType()) {
		case TransferContract:
				return unpack(TransferContract.parseFrom(contractByteString));
		case TransferAssetContract:
			return unpack(TransferAssetContract.parseFrom(contractByteString));
		case TriggerSmartContract:
			return unpack(TriggerSmartContract.parseFrom(contractByteString));
		case VoteWitnessContract:
			return unpack(VoteWitnessContract.parseFrom(contractByteString));
		case ExchangeTransactionContract:
			return unpack(VoteWitnessContract.parseFrom(contractByteString));
		default:
			break;
		}
		
		
		
		return null;
	}

	private static TransferAssetContractModel unpack(TransferAssetContract contract) {

		TransferAssetContractModel model = new TransferAssetContractModel();
		
		model.setAmount(contract.getAmount());
		model.setFrom(Wallet.encode58Check(contract.getOwnerAddress().toByteArray()));
		model.setTo(Wallet.encode58Check(contract.getToAddress().toByteArray()));
		model.setAsset(contract.getAssetName().toStringUtf8());
		JsonFormat.printToString(contract);
		
		return model;
	}

	private static TransferContractModel unpack(TransferContract contract) {
		
		TransferContractModel model = new TransferContractModel();
		
		model.setAmount(contract.getAmount());
		model.setFrom(Wallet.encode58Check(contract.getOwnerAddress().toByteArray()));
		model.setTo(Wallet.encode58Check(contract.getToAddress().toByteArray()));
		
		
		return model;
	}
	
	private static TriggerSmartContractModel unpack(TriggerSmartContract contract) {
		
		TriggerSmartContractModel model = new TriggerSmartContractModel();
		
		model.setContractAddress(Wallet.encode58Check(contract.getContractAddress().toByteArray()));
		model.setFrom(Wallet.encode58Check(contract.getOwnerAddress().toByteArray()));
		model.setCallTokenValue(contract.getCallValue());
		//model.setData(contract.getData().toStringUtf8());

		
		return model;
	}
	
	private static VoteWitnessContractModel unpack(VoteWitnessContract contract) {
		
		VoteWitnessContractModel model = new VoteWitnessContractModel();
		
		String ownerAddress = Wallet.encode58Check(contract.getOwnerAddress().toByteArray());
		
		model.setFrom(ownerAddress);
		
		ArrayList<org.tronhook.api.model.contract.Vote> votes = new ArrayList<>();
		
		for(Vote vote:contract.getVotesList()) {
			
			org.tronhook.api.model.contract.Vote v = new org.tronhook.api.model.contract.Vote();
			
			String voteAddress = Wallet.encode58Check(vote.getVoteAddress().toByteArray());

			v.setVoteAddress(voteAddress);
			v.setVoteCount(vote.getVoteCount());

			votes.add(v);
			
		}

		model.setVotes(votes);
		
		return model;
	}
	
	private static ExchangeTransactionContractModel unpack(ExchangeTransactionContract contract) {
		
		ExchangeTransactionContractModel model = new ExchangeTransactionContractModel();
		
		model.setExchangeId(contract.getExchangeId());
		model.setExpected(contract.getExpected());
		model.setQuant(contract.getQuant());
		model.setTokenId(contract.getTokenId().toStringUtf8());
		
		return model;
	}
	
	
	
	
	
}
