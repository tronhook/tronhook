package org.tronhook.api;

public class TronHookException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TronHookException(String msg,Throwable error) {
		super(msg,error);
	}
	
}
