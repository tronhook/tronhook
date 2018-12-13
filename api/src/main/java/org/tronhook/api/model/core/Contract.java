package org.tronhook.api.model.core;

public class Contract {
	
	private ContractTypeModel type;
	
	private Object parameter;
	
	private String provider;
	
	private String ContractName;

	public ContractTypeModel getType() {
		return type;
	}

	public void setType(ContractTypeModel type) {
		this.type = type;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getContractName() {
		return ContractName;
	}

	public void setContractName(String contractName) {
		ContractName = contractName;
	}

}
