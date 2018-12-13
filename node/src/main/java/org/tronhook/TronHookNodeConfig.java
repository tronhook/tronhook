package org.tronhook;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.typesafe.config.Config;

public class TronHookNodeConfig{

	private Config config;
	private String hookId;
	
	@Inject
	public TronHookNodeConfig(Config config,@Named("hook.id")String hookId) {
		this.config = config;
		this.hookId = hookId;
	}
	
	public String getHookId() {
		return hookId;
	}

	public String getNodeId() {
		return this.config.getString("nodeId");
	}
	
	public long getBlockStart() {
		return this.config.getLong("blockStart");
	}
	
	public long getBlockStop() {
		return this.config.getLong("blockStop");
	}

	public int getBlockRefBatchSize() {
		return this.config.getInt("blockRefBatchSize");
	}
	


}
