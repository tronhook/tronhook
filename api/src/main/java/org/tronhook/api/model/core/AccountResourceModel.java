package org.tronhook.api.model.core;

import java.util.List;

public class AccountResourceModel {
	
	private long energyUsage;
	
	private List<FrozenModel> frozenBalanceForEnergy;
	
	private long latestConsumeTimeForEnergy;
	
	private long acquiredDelegatedFrozenBalanceForEnergy;
	
	private long delegatedFrozenBalanceForEnergy;
	
	private long storageLimit;
	
	private long storageUsage;
	
	private long latestExchangeStorageTime;

	public long getEnergyUsage() {
		return energyUsage;
	}

	public void setEnergyUsage(long energyUsage) {
		this.energyUsage = energyUsage;
	}

	public List<FrozenModel> getFrozenBalanceForEnergy() {
		return frozenBalanceForEnergy;
	}

	public void setFrozenBalanceForEnergy(List<FrozenModel> frozenBalanceForEnergy) {
		this.frozenBalanceForEnergy = frozenBalanceForEnergy;
	}

	public long getLatestConsumeTimeForEnergy() {
		return latestConsumeTimeForEnergy;
	}

	public void setLatestConsumeTimeForEnergy(long latestConsumeTimeForEnergy) {
		this.latestConsumeTimeForEnergy = latestConsumeTimeForEnergy;
	}

	public long getAcquiredDelegatedFrozenBalanceForEnergy() {
		return acquiredDelegatedFrozenBalanceForEnergy;
	}

	public void setAcquiredDelegatedFrozenBalanceForEnergy(long acquiredDelegatedFrozenBalanceForEnergy) {
		this.acquiredDelegatedFrozenBalanceForEnergy = acquiredDelegatedFrozenBalanceForEnergy;
	}

	public long getDelegatedFrozenBalanceForEnergy() {
		return delegatedFrozenBalanceForEnergy;
	}

	public void setDelegatedFrozenBalanceForEnergy(long delegatedFrozenBalanceForEnergy) {
		this.delegatedFrozenBalanceForEnergy = delegatedFrozenBalanceForEnergy;
	}

	public long getStorageLimit() {
		return storageLimit;
	}

	public void setStorageLimit(long storageLimit) {
		this.storageLimit = storageLimit;
	}

	public long getStorageUsage() {
		return storageUsage;
	}

	public void setStorageUsage(long storageUsage) {
		this.storageUsage = storageUsage;
	}

	public long getLatestExchangeStorageTime() {
		return latestExchangeStorageTime;
	}

	public void setLatestExchangeStorageTime(long latestExchangeStorageTime) {
		this.latestExchangeStorageTime = latestExchangeStorageTime;
	}

}
