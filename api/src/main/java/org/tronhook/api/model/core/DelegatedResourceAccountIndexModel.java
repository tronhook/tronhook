package org.tronhook.api.model.core;

import java.util.List;

public class DelegatedResourceAccountIndexModel {
	
	private String account;
	
	private List<String> fromAccounts;
	
	private List<String> toAccounts;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public List<String> getFromAccounts() {
		return fromAccounts;
	}

	public void setFromAccounts(List<String> fromAccounts) {
		this.fromAccounts = fromAccounts;
	}

	public List<String> getToAccounts() {
		return toAccounts;
	}

	public void setToAccounts(List<String> toAccounts) {
		this.toAccounts = toAccounts;
	}

}
