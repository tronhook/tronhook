package org.tronhook.api.model.core;

public class VoteModel {
	
	private String voteAddress;

	private long voteCount;

	public String getVoteAddress() {
		return voteAddress;
	}

	public void setVoteAddress(String voteAddress) {
		this.voteAddress = voteAddress;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

}
