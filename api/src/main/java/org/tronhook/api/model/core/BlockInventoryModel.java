package org.tronhook.api.model.core;

import java.util.List;

public class BlockInventoryModel {
	
	private List<BlockIdModel> ids;
	
	private TypeModel type;

	public List<BlockIdModel> getIds() {
		return ids;
	}

	public void setIds(List<BlockIdModel> ids) {
		this.ids = ids;
	}

	public TypeModel getType() {
		return type;
	}

	public void setType(TypeModel type) {
		this.type = type;
	}

}
