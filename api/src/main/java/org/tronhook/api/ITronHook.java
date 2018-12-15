package org.tronhook.api;

import java.util.List;

import org.tronhook.api.model.BlockModel;

public interface ITronHook {

	public void onNodeStart();
	
	public void onNodeStop();
	
	public void processBlocks(List<BlockModel> block) throws TronHookException;
	
	
	
}
