package org.tronhook.api.model.core;

public class BlockHeaderModel {
	
	private long timeStamp;
	
	private String txTrieRoot;
	
	private String parentHash;
	
	private long number;
	
	private long witnessId;
	
	private String witnessAddress;
	
	private long version;
	
	private String witnessSignature;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTxTrieRoot() {
		return txTrieRoot;
	}

	public void setTxTrieRoot(String txTrieRoot) {
		this.txTrieRoot = txTrieRoot;
	}

	public String getParentHash() {
		return parentHash;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getWitnessId() {
		return witnessId;
	}

	public void setWitnessId(long witnessId) {
		this.witnessId = witnessId;
	}

	public String getWitnessAddress() {
		return witnessAddress;
	}

	public void setWitnessAddress(String witnessAddress) {
		this.witnessAddress = witnessAddress;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getWitnessSignature() {
		return witnessSignature;
	}

	public void setWitnessSignature(String witnessSignature) {
		this.witnessSignature = witnessSignature;
	}

}
