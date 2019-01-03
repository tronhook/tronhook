package org.tronhook.api.parser;


import java.util.ArrayList;

import org.tron.core.Wallet;
import org.tron.core.services.http.JsonFormat;
import org.tron.protos.Contract.AccountCreateContract;
import org.tron.protos.Contract.AccountUpdateContract;
import org.tron.protos.Contract.AssetIssueContract;
import org.tron.protos.Contract.AssetIssueContract.FrozenSupply;
import org.tron.protos.Contract.BuyStorageBytesContract;
import org.tron.protos.Contract.CreateSmartContract;
import org.tron.protos.Contract.ExchangeCreateContract;
import org.tron.protos.Contract.ExchangeInjectContract;
import org.tron.protos.Contract.ExchangeTransactionContract;
import org.tron.protos.Contract.ExchangeWithdrawContract;
import org.tron.protos.Contract.FreezeBalanceContract;
import org.tron.protos.Contract.ParticipateAssetIssueContract;
import org.tron.protos.Contract.ProposalApproveContract;
import org.tron.protos.Contract.ProposalCreateContract;
import org.tron.protos.Contract.ProposalDeleteContract;
import org.tron.protos.Contract.SellStorageContract;
import org.tron.protos.Contract.SetAccountIdContract;
import org.tron.protos.Contract.TransferAssetContract;
import org.tron.protos.Contract.TransferContract;
import org.tron.protos.Contract.TriggerSmartContract;
import org.tron.protos.Contract.UnfreezeAssetContract;
import org.tron.protos.Contract.UnfreezeBalanceContract;
import org.tron.protos.Contract.UpdateAssetContract;
import org.tron.protos.Contract.UpdateSettingContract;
import org.tron.protos.Contract.VoteAssetContract;
import org.tron.protos.Contract.VoteWitnessContract;
import org.tron.protos.Contract.VoteWitnessContract.Vote;
import org.tron.protos.Contract.WithdrawBalanceContract;
import org.tron.protos.Contract.WitnessCreateContract;
import org.tron.protos.Contract.WitnessUpdateContract;
import org.tron.protos.Protocol.SmartContract;
import org.tron.protos.Protocol.Transaction.Contract;
import org.tron.protos.Protocol.Transaction.Result.contractResult;
import org.tronhook.api.model.contract.AccountCreateContractModel;
import org.tronhook.api.model.contract.AccountUpdateContractModel;
import org.tronhook.api.model.contract.AssetIssueContractModel;
import org.tronhook.api.model.contract.CreateSmartContractModel;
import org.tronhook.api.model.contract.ExchangeCreateContractModel;
import org.tronhook.api.model.contract.ExchangeInjectContractModel;
import org.tronhook.api.model.contract.ExchangeTransactionContractModel;
import org.tronhook.api.model.contract.ExchangeWithdrawContractModel;
import org.tronhook.api.model.contract.FreezeBalanceContractModel;
import org.tronhook.api.model.contract.ParticipateAssetIssueContractModel;
import org.tronhook.api.model.contract.ProposalApproveContractModel;
import org.tronhook.api.model.contract.ProposalCreateContractModel;
import org.tronhook.api.model.contract.ProposalDeleteContractModel;
import org.tronhook.api.model.contract.SetAccountIdContractModel;
import org.tronhook.api.model.contract.TransferAssetContractModel;
import org.tronhook.api.model.contract.TransferContractModel;
import org.tronhook.api.model.contract.TriggerSmartContractModel;
import org.tronhook.api.model.contract.UnfreezeAssetContractModel;
import org.tronhook.api.model.contract.UnfreezeBalanceContractModel;
import org.tronhook.api.model.contract.UpdateAssetContractModel;
import org.tronhook.api.model.contract.UpdateSettingContractModel;
import org.tronhook.api.model.contract.VoteAssetContractModel;
import org.tronhook.api.model.contract.VoteWitnessContractModel;
import org.tronhook.api.model.contract.WithdrawBalanceContractModel;
import org.tronhook.api.model.contract.WitnessCreateContractModel;
import org.tronhook.api.model.contract.WitnessUpdateContractModel;
import org.tronhook.api.model.core.ABIModel;
import org.tronhook.api.model.core.SmartContractModel;

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
			return unpack(ExchangeTransactionContract.parseFrom(contractByteString));
		case AccountCreateContract:
			return unpack(AccountCreateContract.parseFrom(contractByteString));
		case AccountUpdateContract:
			return unpack(AccountUpdateContract.parseFrom(contractByteString));
		case AssetIssueContract:
			return unpack(AssetIssueContract.parseFrom(contractByteString));
		case CreateSmartContract:
			return unpack(CreateSmartContract.parseFrom(contractByteString));
		case ExchangeCreateContract:
			return unpack(ExchangeCreateContract.parseFrom(contractByteString));
		case ExchangeInjectContract:
			return unpack(ExchangeInjectContract.parseFrom(contractByteString));
		case ExchangeWithdrawContract:
			return unpack(ExchangeWithdrawContract.parseFrom(contractByteString));
		case FreezeBalanceContract:
			return unpack(FreezeBalanceContract.parseFrom(contractByteString));
		case ParticipateAssetIssueContract:
			return unpack(ParticipateAssetIssueContract.parseFrom(contractByteString));
		case ProposalApproveContract:
			return unpack(ProposalApproveContract.parseFrom(contractByteString));
		case ProposalDeleteContract:
			return unpack(ProposalDeleteContract.parseFrom(contractByteString));
		case SetAccountIdContract:
			return unpack(SetAccountIdContract.parseFrom(contractByteString));
		case UnfreezeBalanceContract:
			return unpack(UnfreezeBalanceContract.parseFrom(contractByteString));
		case UpdateAssetContract:
			return unpack(UpdateAssetContract.parseFrom(contractByteString));
		case UpdateSettingContract:
			return unpack(UpdateSettingContract.parseFrom(contractByteString));
		case VoteAssetContract:
			return unpack(VoteAssetContract.parseFrom(contractByteString));
		case WitnessCreateContract:
			return unpack(WitnessCreateContract.parseFrom(contractByteString));
		case WitnessUpdateContract:
			return unpack(WitnessUpdateContract.parseFrom(contractByteString));


		default:
			break;
		}
		
		
		
		return null;
	}


	private static WitnessUpdateContractModel unpack(WitnessUpdateContract contract) {
		
		WitnessUpdateContractModel model = new WitnessUpdateContractModel();
		
		model.setUpdateUrl(contract.getUpdateUrl().toStringUtf8());
		
		return model;
	}


	private static WitnessCreateContractModel unpack(WitnessCreateContract contract) {
		

		WitnessCreateContractModel model = new WitnessCreateContractModel();
		
		model.setUrl(contract.getUrl().toStringUtf8());
		return model;
	}


	private static VoteAssetContractModel unpack(VoteAssetContract contract) {
		
		VoteAssetContractModel model = new VoteAssetContractModel();
		
		model.setCount(contract.getCount());
		model.setSupport(contract.getSupport());
		//model.setVoteAddress(contract.getVoteAddress(index));
		
		return model;
	}


	private static UpdateSettingContractModel unpack(UpdateSettingContract contract) {
		
		UpdateSettingContractModel model = new UpdateSettingContractModel();
		
		model.setConsumeUserResourcePercent(contract.getConsumeUserResourcePercent());
		model.setContractAddress(Wallet.encode58Check(contract.getContractAddress().toByteArray()));
		
		return model;
	}


	private static UpdateAssetContractModel unpack(UpdateAssetContract contract) {
		
		UpdateAssetContractModel model = new UpdateAssetContractModel();
		
		model.setDescription(contract.getDescription().toStringUtf8());
		model.setNewLimit(contract.getNewLimit());
		model.setNewPublicLimit(contract.getNewPublicLimit());
		model.setUrl(contract.getUrl().toStringUtf8());
		
		return model;
	}


	private static UnfreezeBalanceContractModel unpack(UnfreezeBalanceContract contract) {
		
		UnfreezeBalanceContractModel model = new UnfreezeBalanceContractModel();
		
		//model.setReceiverAddress(contract.);
		//model.setResource(contract.getResource());
		return model;
	}


	private static SetAccountIdContractModel unpack(SetAccountIdContract contract) {
		
		SetAccountIdContractModel model = new SetAccountIdContractModel();
		
		model.setAccountId(contract.getAccountId().toStringUtf8());
		
		return null;
	}


	private static ProposalDeleteContractModel unpack(ProposalDeleteContract contract) {
		
		ProposalDeleteContractModel model = new ProposalDeleteContractModel();
		
		model.setProposalId(contract.getProposalId());
		
		return model;
	}


	private static ProposalApproveContractModel unpack(ProposalApproveContract contract) {
		
		ProposalApproveContractModel model = new ProposalApproveContractModel();
		
		model.setAddApproval(contract.getIsAddApproval());
		model.setProposalId(contract.getProposalId());
		
		return model;
	}



	private static ParticipateAssetIssueContractModel unpack(ParticipateAssetIssueContract contract) {
		
		ParticipateAssetIssueContractModel model = new ParticipateAssetIssueContractModel();
		
		model.setAmount(contract.getAmount());
		model.setAssetName(contract.getAssetName().toStringUtf8());
		
		return model;
	}



	private static FreezeBalanceContractModel unpack(FreezeBalanceContract contract) {
		
		
		FreezeBalanceContractModel model = new FreezeBalanceContractModel();
		
		model.setFrozenBalance(contract.getFrozenBalance());
		model.setFrozenDuration(contract.getFrozenDuration());
		//model.setReceiverAddress(contract.);
		//model.setResource(contract.getResource());
	
		return null;
	}



	private static ExchangeWithdrawContractModel unpack(ExchangeWithdrawContract contract) {
		
		ExchangeWithdrawContractModel model = new ExchangeWithdrawContractModel();
		
		model.setExchangeId(contract.getExchangeId());
		model.setQuant(contract.getQuant());
		model.setTokenId(contract.getTokenId().toStringUtf8());
		
		return model;
	}



	private static ExchangeInjectContractModel unpack(ExchangeInjectContract contract) {
		
		ExchangeInjectContractModel model = new ExchangeInjectContractModel();
		
		model.setExchangeId(contract.getExchangeId());
		model.setQuant(contract.getQuant());
		model.setTokenId(contract.getTokenId().toStringUtf8());
		
		return model;
	}



	private static ExchangeCreateContractModel unpack(ExchangeCreateContract contract) {
		
		ExchangeCreateContractModel model = new ExchangeCreateContractModel();
		
		model.setFirstTokenBalance(contract.getFirstTokenBalance());
		model.setFirstTokenId(contract.getFirstTokenId().toStringUtf8());
		model.setSecondTokenBalance(contract.getSecondTokenBalance());
		model.setSecondTokenId(contract.getSecondTokenId().toStringUtf8());

		return model;
	}



	private static CreateSmartContractModel unpack(CreateSmartContract contract) {
		
		CreateSmartContractModel model = new CreateSmartContractModel();
		
		//model.setCallTokenValue(contract.);
		SmartContract smartContract = contract.getNewContract();
		SmartContractModel scontract = new SmartContractModel();
		
		
		ABIModel abimodel = new ABIModel();
		//abimodel.setEntrys(entrys);
 		
		//scontract.setAbi();
		scontract.setBytecode(smartContract.getBytecode().toStringUtf8());
		model.setNewContract(scontract);
		//model.setTokenId(contract);
		
		
		return model;
	}



	private static AssetIssueContractModel unpack(AssetIssueContract contract) {
		
		AssetIssueContractModel model = new AssetIssueContractModel();
		
		model.setAbbr(contract.getAbbr().toStringUtf8());
		model.setDescription(contract.getDescription().toStringUtf8());
		model.setEndTime(contract.getEndTime());
		model.setFreeAssetNetLimit(contract.getFreeAssetNetLimit());
		
		ArrayList<org.tronhook.api.model.contract.FrozenSupply> fslist = new ArrayList<>();
		
		for (FrozenSupply fs : contract.getFrozenSupplyList()) {
			
			org.tronhook.api.model.contract.FrozenSupply fsmodel = new org.tronhook.api.model.contract.FrozenSupply();
			
			fsmodel.setFrozenAmount(fs.getFrozenAmount());		
			fsmodel.setFrozenDays(fs.getFrozenDays());		
					
			fslist.add(fsmodel);
		}
		model.setFrozenSupply(fslist);
		//model.setId(contract.get);
		model.setName(contract.getName().toStringUtf8());
		model.setNum(contract.getNum());
		model.setOrder(contract.getOrder());
		//model.setPrecision(contract.get);
		model.setPublicFreeAssetNetLimit(contract.getPublicFreeAssetNetLimit());
		model.setPublicFreeAssetNetUsage(contract.getPublicFreeAssetNetUsage());
		model.setPublicLatestFreeNetTime(contract.getPublicLatestFreeNetTime());
		model.setStartTime(contract.getStartTime());
		model.setTotalSupply(contract.getTotalSupply());
		model.setTrxNum(contract.getTrxNum());
		model.setUrl(contract.getUrl().toStringUtf8());
		model.setVoteScore(contract.getVoteScore());
		
		return model;
	}

	private static AccountUpdateContractModel unpack(AccountUpdateContract contract) {
		
		AccountUpdateContractModel model = new AccountUpdateContractModel();
		
		model.setAccountName(contract.getAccountName().toStringUtf8());
		
		return model;
	}

	private static AccountCreateContractModel unpack(AccountCreateContract contract) {
		
		AccountCreateContractModel model = new AccountCreateContractModel();
		
		model.setAccountAddress(Wallet.encode58Check(contract.getOwnerAddress().toByteArray()));
		//model.setType(contract.getType());
		
		return model;
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
