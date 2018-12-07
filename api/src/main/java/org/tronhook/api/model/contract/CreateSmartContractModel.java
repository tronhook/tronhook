package org.tronhook.api.model.contract;

public class CreateSmartContractModel {
	
	private String from;
	
	private SmartContractModel newContract;
	
	private long callTokenValue;
	
	private long tokenId;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public SmartContractModel getNewContract() {
		return newContract;
	}

	public void setNewContract(SmartContractModel newContract) {
		this.newContract = newContract;
	}

	public long getCallTokenValue() {
		return callTokenValue;
	}

	public void setCallTokenValue(long callTokenValue) {
		this.callTokenValue = callTokenValue;
	}

	public long getTokenId() {
		return tokenId;
	}

	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	} 

}
