package org.tronhook.api.model.contract;

import java.util.List;

public class UnfreezeBalanceContractModel {
	
	private String from;
	
	private ResourceCode resource;
	
	private String receiverAddress;

	public ResourceCode getResource() {
		return resource;
	}

	public void setResource(ResourceCode resource) {
		this.resource = resource;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

}
