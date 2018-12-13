package org.tronhook.api.model.core;

import java.util.List;

public class InventoryModel {
	
	private InventoryTypeModel type;
	
	private List<String> ids;

	public InventoryTypeModel getType() {
		return type;
	}

	public void setType(InventoryTypeModel type) {
		this.type = type;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
