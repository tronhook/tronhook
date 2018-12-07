package org.tronhook.api.model.contract;

public class ExchangeTransactionContractModel {
	
	private String from;
	
	private long exchangeId;
	
	private String tokenId;
	
	private long quant;

	private long expected;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public long getQuant() {
		return quant;
	}

	public void setQuant(long quant) {
		this.quant = quant;
	}

	public long getExpected() {
		return expected;
	}

	public void setExpected(long expected) {
		this.expected = expected;
	}
}
