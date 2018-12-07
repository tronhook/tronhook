package org.tronhook.api.model.contract;

public class UpdateEnergyLimitContractModel {
	
	private String from;
	
	private String contractAddress;
	
	private long originEnergyLimit;

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

	public long getOriginEnergyLimit() {
		return originEnergyLimit;
	}

	public void setOriginEnergyLimit(long originEnergyLimit) {
		this.originEnergyLimit = originEnergyLimit;
	}

}
