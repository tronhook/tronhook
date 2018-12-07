package org.tronhook.api.model.contract;

public class BuyStorageBytesContractModel {
	
	private String from;
	
	private long bytes;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
}
