package org.tronhook.api;

import com.typesafe.config.Config;

public abstract class TronHook implements ITronHook{
	
	private Config config;
	
	private BlockInfo blockInfo;
	
	public TronHook(Config config) {
		this.config = config;
	}
	
	
	
	protected Config getConfig() {
		return this.config;
	}



	public BlockInfo getBlockInfo() {
		return blockInfo;
	}




	public void setBlockInfo(BlockInfo blockInfo) {
		this.blockInfo = blockInfo;
	}




	public void setConfig(Config config) {
		this.config = config;
	}
	
	
	
}
