package org.tronhook.api.model.core;

import java.util.List;

public class EntryModel {
	
	private boolean anonymous;
	
	private boolean constant;
	
	private String name;
	
	private List<ParamModel> inputs;
	
	private List<ParamModel> outputs;
	
	private EntryTypeModel type;
	
	private boolean payable;
	
	private StateMutabilityTypeModel stateMutability;

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public boolean isConstant() {
		return constant;
	}

	public void setConstant(boolean constant) {
		this.constant = constant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ParamModel> getInputs() {
		return inputs;
	}

	public void setInputs(List<ParamModel> inputs) {
		this.inputs = inputs;
	}

	public List<ParamModel> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<ParamModel> outputs) {
		this.outputs = outputs;
	}

	public EntryTypeModel getType() {
		return type;
	}

	public void setType(EntryTypeModel type) {
		this.type = type;
	}

	public boolean isPayable() {
		return payable;
	}

	public void setPayable(boolean payable) {
		this.payable = payable;
	}

	public StateMutabilityTypeModel getStateMutability() {
		return stateMutability;
	}

	public void setStateMutability(StateMutabilityTypeModel stateMutability) {
		this.stateMutability = stateMutability;
	}

}
