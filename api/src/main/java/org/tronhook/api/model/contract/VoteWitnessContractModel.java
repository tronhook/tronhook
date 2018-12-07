package org.tronhook.api.model.contract;

import java.util.List;

public class VoteWitnessContractModel {
	
	private String from;
	
	private List<Vote> votes;
	
	private boolean support;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public boolean isSupport() {
		return support;
	}

	public void setSupport(boolean support) {
		this.support = support;
	}

}
