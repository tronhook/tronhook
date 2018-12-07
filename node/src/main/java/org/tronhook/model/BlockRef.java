package org.tronhook.model;

public class BlockRef {

	private String _id;
	
	private long number;
	
	private int tries;
	
	private String error;
	
	private Boolean processed; 
	
	private BlockRef() {
	}
	
	public BlockRef(long number) {
		this.number = number;
	}
	
	
	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public long getNumber() {
		return Long.valueOf(_id);
	}



	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}


	
	
	
}
