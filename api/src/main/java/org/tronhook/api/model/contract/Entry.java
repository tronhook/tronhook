package org.tronhook.api.model.contract;

import java.util.List;

public class Entry {
	
	private boolean anonymous;
	
	private boolean constant;
	
	private String name;
	
	private List<Param> inputs;
	
	private List<Param> outputs;
	
	private EntryType type;
	
	private boolean payable;
	
	private StateMutabilityType stateMutability;

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

	public List<Param> getInputs() {
		return inputs;
	}

	public void setInputs(List<Param> inputs) {
		this.inputs = inputs;
	}

	public List<Param> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Param> outputs) {
		this.outputs = outputs;
	}

	public EntryType getType() {
		return type;
	}

	public void setType(EntryType type) {
		this.type = type;
	}

	public boolean isPayable() {
		return payable;
	}

	public void setPayable(boolean payable) {
		this.payable = payable;
	}

	public StateMutabilityType getStateMutability() {
		return stateMutability;
	}

	public void setStateMutability(StateMutabilityType stateMutability) {
		this.stateMutability = stateMutability;
	}

}
