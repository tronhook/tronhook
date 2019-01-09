package org.tronhook.api.model.contract;

import org.tronhook.api.model.core.SmartContractModel;

public class CreateSmartContractModel {
	
	private String from;
	
	private SmartContractModel newContract;
	
	private long callTokenValue;
	
	private String tokenId;

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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	} 

}
