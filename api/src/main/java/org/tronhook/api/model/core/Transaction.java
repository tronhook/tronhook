package org.tronhook.api.model.core;

import java.util.List;

public class Transaction {
	
	private String refBlockBytes;

	private long refBlockNum;
	
	private String refBlockHash;

	private long expiration;
	
	private List<AuthorityModel> auths;
	
	private String data;
	
	private List<Contract> contract;
	
	private String scripts;

	private long timestamp;
	
	private long feeLimit;
	
	private List<String> signature;
	
	private List<ResultModel> ret;

	public String getRefBlockBytes() {
		return refBlockBytes;
	}

	public void setRefBlockBytes(String refBlockBytes) {
		this.refBlockBytes = refBlockBytes;
	}

	public long getRefBlockNum() {
		return refBlockNum;
	}

	public void setRefBlockNum(long refBlockNum) {
		this.refBlockNum = refBlockNum;
	}

	public String getRefBlockHash() {
		return refBlockHash;
	}

	public void setRefBlockHash(String refBlockHash) {
		this.refBlockHash = refBlockHash;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public List<AuthorityModel> getAuths() {
		return auths;
	}

	public void setAuths(List<AuthorityModel> auths) {
		this.auths = auths;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Contract> getContract() {
		return contract;
	}

	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}

	public String getScripts() {
		return scripts;
	}

	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getFeeLimit() {
		return feeLimit;
	}

	public void setFeeLimit(long feeLimit) {
		this.feeLimit = feeLimit;
	}

	public List<String> getSignature() {
		return signature;
	}

	public void setSignature(List<String> signature) {
		this.signature = signature;
	}

	public List<ResultModel> getRet() {
		return ret;
	}

	public void setRet(List<ResultModel> ret) {
		this.ret = ret;
	}

}
