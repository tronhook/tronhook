package org.tronhook.api.model.core;

import java.util.List;

public class InternalTransactionModel {
	
	private String hash;
	
	private String callerAddress;
	
	private String transferToAddress;
	
	private List<CallValueInfoModel> callValueInfo;
	
	private String note;
	
	private boolean rejected;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getCallerAddress() {
		return callerAddress;
	}

	public void setCallerAddress(String callerAddress) {
		this.callerAddress = callerAddress;
	}

	public String getTransferToAddress() {
		return transferToAddress;
	}

	public void setTransferToAddress(String transferToAddress) {
		this.transferToAddress = transferToAddress;
	}

	public List<CallValueInfoModel> getCallValueInfo() {
		return callValueInfo;
	}

	public void setCallValueInfo(List<CallValueInfoModel> callValueInfo) {
		this.callValueInfo = callValueInfo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
}
