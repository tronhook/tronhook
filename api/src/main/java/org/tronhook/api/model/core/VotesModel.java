package org.tronhook.api.model.core;

import java.util.List;

public class VotesModel {
	
	private String address;
	
	private List<VoteModel> oldVotes;
	
	private List<VoteModel> newVotes;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<VoteModel> getOldVotes() {
		return oldVotes;
	}

	public void setOldVotes(List<VoteModel> oldVotes) {
		this.oldVotes = oldVotes;
	}

	public List<VoteModel> getNewVotes() {
		return newVotes;
	}

	public void setNewVotes(List<VoteModel> newVotes) {
		this.newVotes = newVotes;
	}

}
