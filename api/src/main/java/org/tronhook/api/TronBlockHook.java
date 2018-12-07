package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.BlockModel;

import com.typesafe.config.Config;

public abstract class TronBlockHook extends TronHook{

	
	public TronBlockHook(Config config) {
		super(config);
	}

	public abstract void processBlocks(List<BlockModel> block) throws TronHookException;
	
}
