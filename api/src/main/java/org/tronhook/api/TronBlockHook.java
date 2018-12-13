package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.BlockModel;

import com.typesafe.config.Config;

public abstract class TronBlockHook extends TronHook implements ITronBlockHook{

	
	public TronBlockHook(Config config) {
		super(config);
	}

	public abstract void processBlocks(List<BlockModel> blocks) throws TronHookException;
	
}
