package org.tronhook.api.model.core;

public class ResourceReceiptModel {
	
	private long energyUsage;
	
	private long energyFee;
	
	private long originEnergyUsage;
	
	private long energyUsageTotal;
	
	private long netUsage;
	
	private long netFee;
	
	private ContractResultModel result;

	public long getEnergyUsage() {
		return energyUsage;
	}

	public void setEnergyUsage(long energyUsage) {
		this.energyUsage = energyUsage;
	}

	public long getEnergyFee() {
		return energyFee;
	}

	public void setEnergyFee(long energyFee) {
		this.energyFee = energyFee;
	}

	public long getOriginEnergyUsage() {
		return originEnergyUsage;
	}

	public void setOriginEnergyUsage(long originEnergyUsage) {
		this.originEnergyUsage = originEnergyUsage;
	}

	public long getEnergyUsageTotal() {
		return energyUsageTotal;
	}

	public void setEnergyUsageTotal(long energyUsageTotal) {
		this.energyUsageTotal = energyUsageTotal;
	}

	public long getNetUsage() {
		return netUsage;
	}

	public void setNetUsage(long netUsage) {
		this.netUsage = netUsage;
	}

	public long getNetFee() {
		return netFee;
	}

	public void setNetFee(long netFee) {
		this.netFee = netFee;
	}

	public ContractResultModel getResult() {
		return result;
	}

	public void setResult(ContractResultModel result) {
		this.result = result;
	}

}
