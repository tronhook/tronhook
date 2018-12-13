package org.tronhook.api.model.core;

import java.util.List;

public class TransactionInfoModel {
	
	private String id;

	private long fee;
	
	private long blockNumber;
	
	private long blockTimeStamp;
	
	private List<String> contractResult;
	
	private String contractAddress;
	
	private ResourceReceiptModel receipt;
	
	private List<LogModel> log;
	
	private CodeModel result;
	
	private String resMessage;
	
	private String assetIssueId;
	
	private long withdrawAmount;
	
	private long unfreezeAmount;
	
	private List<InternalTransactionModel> internalTransactions;
	
	private long exchangeReceivedAmount;
	
	private long exchangeInjectAnotherAmount;
	
	private long exchangeWithdrawAnotherAmount;
	
	private long exchangeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public long getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public long getBlockTimeStamp() {
		return blockTimeStamp;
	}

	public void setBlockTimeStamp(long blockTimeStamp) {
		this.blockTimeStamp = blockTimeStamp;
	}

	public List<String> getContractResult() {
		return contractResult;
	}

	public void setContractResult(List<String> contractResult) {
		this.contractResult = contractResult;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public ResourceReceiptModel getReceipt() {
		return receipt;
	}

	public void setReceipt(ResourceReceiptModel receipt) {
		this.receipt = receipt;
	}

	public List<LogModel> getLog() {
		return log;
	}

	public void setLog(List<LogModel> log) {
		this.log = log;
	}

	public CodeModel getResult() {
		return result;
	}

	public void setResult(CodeModel result) {
		this.result = result;
	}

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public String getAssetIssueId() {
		return assetIssueId;
	}

	public void setAssetIssueId(String assetIssueId) {
		this.assetIssueId = assetIssueId;
	}

	public long getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(long withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public long getUnfreezeAmount() {
		return unfreezeAmount;
	}

	public void setUnfreezeAmount(long unfreezeAmount) {
		this.unfreezeAmount = unfreezeAmount;
	}

	public List<InternalTransactionModel> getInternalTransactions() {
		return internalTransactions;
	}

	public void setInternalTransactions(List<InternalTransactionModel> internalTransactions) {
		this.internalTransactions = internalTransactions;
	}

	public long getExchangeReceivedAmount() {
		return exchangeReceivedAmount;
	}

	public void setExchangeReceivedAmount(long exchangeReceivedAmount) {
		this.exchangeReceivedAmount = exchangeReceivedAmount;
	}

	public long getExchangeInjectAnotherAmount() {
		return exchangeInjectAnotherAmount;
	}

	public void setExchangeInjectAnotherAmount(long exchangeInjectAnotherAmount) {
		this.exchangeInjectAnotherAmount = exchangeInjectAnotherAmount;
	}

	public long getExchangeWithdrawAnotherAmount() {
		return exchangeWithdrawAnotherAmount;
	}

	public void setExchangeWithdrawAnotherAmount(long exchangeWithdrawAnotherAmount) {
		this.exchangeWithdrawAnotherAmount = exchangeWithdrawAnotherAmount;
	}

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}	
}
