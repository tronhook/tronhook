package org.tronhook.api.model.contract;

import java.util.List;

public class VoteAssetContractModel {

	private String from;
	
	private List<String> voteAddress;
	
	private int count;
	
	private boolean support;
	
	
	public boolean isSupport() {
		return support;
	}

	public void setSupport(boolean support) {
		this.support = support;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getVoteAddress() {
		return voteAddress;
	}

	public void setVoteAddress(List<String> voteAddress) {
		this.voteAddress = voteAddress;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
