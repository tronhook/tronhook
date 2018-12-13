package org.tronhook.api.model.core;

import java.util.List;

public class ChainInventoryModel {
	
	private List<BlockIdModel> ids;
	
	private long remainNum;

	public List<BlockIdModel> getIds() {
		return ids;
	}

	public void setIds(List<BlockIdModel> ids) {
		this.ids = ids;
	}

	public long getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(long remainNum) {
		this.remainNum = remainNum;
	}

}
