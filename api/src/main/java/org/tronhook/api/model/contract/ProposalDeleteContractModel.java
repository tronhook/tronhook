package org.tronhook.api.model.contract;

public class ProposalDeleteContractModel {
	
	private String from;
	
	private long proposalId;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getProposalId() {
		return proposalId;
	}

	public void setProposalId(long proposalId) {
		this.proposalId = proposalId;
	}

}
