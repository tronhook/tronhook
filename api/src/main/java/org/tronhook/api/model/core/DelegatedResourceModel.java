package org.tronhook.api.model.core;

public class DelegatedResourceModel {
	
	private String from;
	
	private String to;

	private long frozenBalanceForBandwidth;
	
	private long frozenBalanceForEnergy;
	
	private long expireTimeForBandwidth;
	
	private long expireTimeForEnergy;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public long getFrozenBalanceForBandwidth() {
		return frozenBalanceForBandwidth;
	}

	public void setFrozenBalanceForBandwidth(long frozenBalanceForBandwidth) {
		this.frozenBalanceForBandwidth = frozenBalanceForBandwidth;
	}

	public long getFrozenBalanceForEnergy() {
		return frozenBalanceForEnergy;
	}

	public void setFrozenBalanceForEnergy(long frozenBalanceForEnergy) {
		this.frozenBalanceForEnergy = frozenBalanceForEnergy;
	}

	public long getExpireTimeForBandwidth() {
		return expireTimeForBandwidth;
	}

	public void setExpireTimeForBandwidth(long expireTimeForBandwidth) {
		this.expireTimeForBandwidth = expireTimeForBandwidth;
	}

	public long getExpireTimeForEnergy() {
		return expireTimeForEnergy;
	}

	public void setExpireTimeForEnergy(long expireTimeForEnergy) {
		this.expireTimeForEnergy = expireTimeForEnergy;
	}
}
