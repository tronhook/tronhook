package org.tronhook.api.model.contract;

public class SellStorageContractModel {
	
	private String from;
	
	private long storageBytes;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getStorageBytes() {
		return storageBytes;
	}

	public void setStorageBytes(long storageBytes) {
		this.storageBytes = storageBytes;
	}

}
