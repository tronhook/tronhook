package org.tronhook.api;

import com.google.inject.Inject;
import com.typesafe.config.Config;

public abstract class TronHook implements ITronHook{
	
	private Config config;
	
	public TronHook(Config config) {
		this.config = config;
	}
	
	
	
	
	protected Config getConfig() {
		return this.config;
	}

}
