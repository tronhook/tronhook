package org.tronhook.api.model.contract;

public class UpdateSettingContractModel {
	
	private String from;
	
	private String contractAddress;
	
	private long consumeUserResourcePercent;

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

	public long getConsumeUserResourcePercent() {
		return consumeUserResourcePercent;
	}

	public void setConsumeUserResourcePercent(long consumeUserResourcePercent) {
		this.consumeUserResourcePercent = consumeUserResourcePercent;
	}

}
