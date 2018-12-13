package org.tronhook.api.model.contract;

import java.util.List;

public class AssetIssueContractModel {
	
	private String id;
	
	private String from;
	
	private String name;
	
	private String abbr;
	
	private long totalSupply;
	
	private List<FrozenSupply> frozenSupply;
	
	private int trxNum;
	
	private int precision;
	
	private int num;
	
	private long startTime;
	
	private long endTime;
	
	private long order;
	
	private int voteScore;
	
	private String description;
	
	private String url;
	
	private long freeAssetNetLimit;
	
	private long publicFreeAssetNetLimit;
	
	private long publicFreeAssetNetUsage;
	
	private long publicLatestFreeNetTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public long getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(long totalSupply) {
		this.totalSupply = totalSupply;
	}

	public List<FrozenSupply> getFrozenSupply() {
		return frozenSupply;
	}

	public void setFrozenSupply(List<FrozenSupply> frozenSupply) {
		this.frozenSupply = frozenSupply;
	}

	public int getTrxNum() {
		return trxNum;
	}

	public void setTrxNum(int trxNum) {
		this.trxNum = trxNum;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getOrder() {
		return order;
	}

	public void setOrder(long order) {
		this.order = order;
	}

	public int getVoteScore() {
		return voteScore;
	}

	public void setVoteScore(int voteScore) {
		this.voteScore = voteScore;
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

	public long getFreeAssetNetLimit() {
		return freeAssetNetLimit;
	}

	public void setFreeAssetNetLimit(long freeAssetNetLimit) {
		this.freeAssetNetLimit = freeAssetNetLimit;
	}

	public long getPublicFreeAssetNetLimit() {
		return publicFreeAssetNetLimit;
	}

	public void setPublicFreeAssetNetLimit(long publicFreeAssetNetLimit) {
		this.publicFreeAssetNetLimit = publicFreeAssetNetLimit;
	}

	public long getPublicFreeAssetNetUsage() {
		return publicFreeAssetNetUsage;
	}

	public void setPublicFreeAssetNetUsage(long publicFreeAssetNetUsage) {
		this.publicFreeAssetNetUsage = publicFreeAssetNetUsage;
	}

	public long getPublicLatestFreeNetTime() {
		return publicLatestFreeNetTime;
	}

	public void setPublicLatestFreeNetTime(long publicLatestFreeNetTime) {
		this.publicLatestFreeNetTime = publicLatestFreeNetTime;
	}
}
