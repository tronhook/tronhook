package org.tronhook;

public class Helper {

	
	public static String getBlockCollectionName(TronHookNodeConfig config) {
		
		return (config.getHookId()+"_"+config.getNodeId()+"_"+config.getNodeType()+"_blocks").toLowerCase();
	}
	
}
