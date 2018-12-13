package org.tronhook.api.model.core;

public class ResultModel {
	
	private long fee;
	
	private CodeModel ret;
	
	private ContractResultModel contractRet;
	
	private String assetIssueId;

	private long withdrawAmount;
	
	private long unfreezeAmount;
	
	private long exchangeReceivedAmount;
	
	private long exchangeInjectAnotherAmount;
	
	private long exchangeWithdrawAnotherAmount;
	
	private long exchangeId;

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public CodeModel getRet() {
		return ret;
	}

	public void setRet(CodeModel ret) {
		this.ret = ret;
	}

	public ContractResultModel getContractRet() {
		return contractRet;
	}

	public void setContractRet(ContractResultModel contractRet) {
		this.contractRet = contractRet;
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
