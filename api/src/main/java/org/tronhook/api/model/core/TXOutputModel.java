package org.tronhook.api.model.core;

public class TXOutputModel {
	
	private long value;
	
	private String pubKeyHash;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getPubKeyHash() {
		return pubKeyHash;
	}

	public void setPubKeyHash(String pubKeyHash) {
		this.pubKeyHash = pubKeyHash;
	}

}
