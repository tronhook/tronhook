package org.tronhook.api.model.contract;

public class UpdateAssetContractModel {
	
	private String from;
	
	private String description;
	
	private String url;
	
	private long newLimit;
	
	private long newPublicLimit;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getNewLimit() {
		return newLimit;
	}

	public void setNewLimit(long newLimit) {
		this.newLimit = newLimit;
	}

	public long getNewPublicLimit() {
		return newPublicLimit;
	}

	public void setNewPublicLimit(long newPublicLimit) {
		this.newPublicLimit = newPublicLimit;
	}

}
