package org.tronhook.api.model.contract;

public class ProposalApproveContractModel {
	
	private String from;
	
	private long proposalId;
	
	private boolean isAddApproval;

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

	public boolean isAddApproval() {
		return isAddApproval;
	}

	public void setAddApproval(boolean isAddApproval) {
		this.isAddApproval = isAddApproval;
	}

}
