package org.tronhook.api.model.contract;

public class ExchangeCreateContractModel {
	
	private String from;
	
	private String firstTokenId;
	
	private long firstTokenBalance;
	
	private String secondTokenId;
	
	private long secondTokenBalance;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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
