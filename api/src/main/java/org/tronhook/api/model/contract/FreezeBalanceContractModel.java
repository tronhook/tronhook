package org.tronhook.api.model.contract;


public class FreezeBalanceContractModel {
	
	private String from;
	
	private long frozenBalance;
	
	private long frozenDuration;
	
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

	public long getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(long frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public long getFrozenDuration() {
		return frozenDuration;
	}

	public void setFrozenDuration(long frozenDuration) {
		this.frozenDuration = frozenDuration;
	}


	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

}
