package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.BlockModel;

public interface ITronBlockHook extends ITronHook{

	public void processBlocks(List<BlockModel> block) throws TronHookException;
	
}
