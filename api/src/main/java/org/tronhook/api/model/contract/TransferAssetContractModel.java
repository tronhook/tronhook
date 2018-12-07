package org.tronhook.api.model.contract;

public class TransferAssetContractModel {
	
	private String asset;//was asset name prior to ALLOW_SAME_TOKEN_NAME after becomes asset id 
	
	private String from;
	
	private String to;
	
	private long amount;

	public String getAsset() {
		return asset;
	}
	
	public void setAsset(String asset) {
		this.asset = asset;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
}
