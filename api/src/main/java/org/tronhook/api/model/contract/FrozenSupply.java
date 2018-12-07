package org.tronhook.api.model.contract;

public class FrozenSupply {
	
	private long frozenAmount;
	
	private long frozenDays;

	public long getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(long frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public long getFrozenDays() {
		return frozenDays;
	}

	public void setFrozenDays(long frozenDays) {
		this.frozenDays = frozenDays;
	}

}
