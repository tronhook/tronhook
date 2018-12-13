package org.tronhook.api.model.core;

public class ExchangeModel {
	
	private long exchangeId;
	
	private String creatorAddress;
	
	private long createTime;
	
	private String firstTokenId;
	
	private long firstTokenBalance;
	
	private String secondTokenId;
	
	private long secondTokenBalance;

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getCreatorAddress() {
		return creatorAddress;
	}

	public void setCreatorAddress(String creatorAddress) {
		this.creatorAddress = creatorAddress;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getFirstTokenId() {
		return firstTokenId;
	}

	public void setFirstTokenId(String firstTokenId) {
		this.firstTokenId = firstTokenId;
	}

	public long getFirstTokenBalance() {
		return firstTokenBalance;
	}

	public void setFirstTokenBalance(long firstTokenBalance) {
		this.firstTokenBalance = firstTokenBalance;
	}

	public String getSecondTokenId() {
		return secondTokenId;
	}

	public void setSecondTokenId(String secondTokenId) {
		this.secondTokenId = secondTokenId;
	}

	public long getSecondTokenBalance() {
		return secondTokenBalance;
	}

	public void setSecondTokenBalance(long secondTokenBalance) {
		this.secondTokenBalance = secondTokenBalance;
	}

}
