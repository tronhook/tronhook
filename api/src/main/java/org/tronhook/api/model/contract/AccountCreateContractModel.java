package org.tronhook.api.model.contract;

import org.tronhook.api.model.core.AccountTypeModel;

public class AccountCreateContractModel {
	
	private String from;
	
	private String accountAddress;
	
	private AccountTypeModel type;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public AccountTypeModel getType() {
		return type;
	}

	public void setType(AccountTypeModel type) {
		this.type = type;
	}
}
