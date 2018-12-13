package org.tronhook.api.model.core;

public class WitnessModel {
	
	private String address;

	private long voteCount;
	
	private String pubKey;
	
	private String url;
	
	private long totalProduced;
	
	private long totalMissed;
	
	private long latestBlockNum;
	
	private long latestSlotNum;
	
	private boolean isJobs;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getTotalProduced() {
		return totalProduced;
	}

	public void setTotalProduced(long totalProduced) {
		this.totalProduced = totalProduced;
	}

	public long getTotalMissed() {
		return totalMissed;
	}

	public void setTotalMissed(long totalMissed) {
		this.totalMissed = totalMissed;
	}

	public long getLatestBlockNum() {
		return latestBlockNum;
	}

	public void setLatestBlockNum(long latestBlockNum) {
		this.latestBlockNum = latestBlockNum;
	}

	public long getLatestSlotNum() {
		return latestSlotNum;
	}

	public void setLatestSlotNum(long latestSlotNum) {
		this.latestSlotNum = latestSlotNum;
	}

	public boolean isJobs() {
		return isJobs;
	}

	public void setJobs(boolean isJobs) {
		this.isJobs = isJobs;
	}

}
