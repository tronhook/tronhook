package org.tronhook.api.model.contract;

public class TriggerSmartContractModel {
	
	private String from;
	
	private String contractAddress;
	
	private long callValue;
	
	private String data;
	
	private long callTokenValue;
	
	private String tokenId;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public long getCallValue() {
		return callValue;
	}

	public void setCallValue(long callValue) {
		this.callValue = callValue;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getCallTokenValue() {
		return callTokenValue;
	}

	public void setCallTokenValue(long callTokenValue) {
		this.callTokenValue = callTokenValue;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

}
