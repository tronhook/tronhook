package org.tronhook.api.model.core;

public class AuthorityModel {
	
	private AccountIdModel account;
	
	private String permissionName;

	public AccountIdModel getAccount() {
		return account;
	}

	public void setAccount(AccountIdModel account) {
		this.account = account;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}
