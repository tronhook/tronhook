package org.tronhook.api.model.core;

public class SmartContractModel {
	
	private String originAddress;
	
	private String contractAddress;
	
	private ABIModel abi;
	
	private String bytecode;
	
	private long callValue;
	
	private long consumeUserResourcePercent;
	
	private String name;
	
	private long originEnergyLimit;

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public ABIModel getAbi() {
		return abi;
	}

	public void setAbi(ABIModel abi) {
		this.abi = abi;
	}

	public String getBytecode() {
		return bytecode;
	}

	public void setBytecode(String bytecode) {
		this.bytecode = bytecode;
	}

	public long getCallValue() {
		return callValue;
	}

	public void setCallValue(long callValue) {
		this.callValue = callValue;
	}

	public long getConsumeUserResourcePercent() {
		return consumeUserResourcePercent;
	}

	public void setConsumeUserResourcePercent(long consumeUserResourcePercent) {
		this.consumeUserResourcePercent = consumeUserResourcePercent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOriginEnergyLimit() {
		return originEnergyLimit;
	}

	public void setOriginEnergyLimit(long originEnergyLimit) {
		this.originEnergyLimit = originEnergyLimit;
	}

}
