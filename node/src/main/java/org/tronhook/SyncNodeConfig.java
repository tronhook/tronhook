package org.tronhook;

import java.util.List;

import com.google.inject.Inject;
import com.typesafe.config.Config;

public class SyncNodeConfig{

	private Config config;
	
	@Inject
	public SyncNodeConfig(Config config) {
		this.config = config;
	}
	

	public int getSynchronizerId() {
		return this.config.getInt("job.synchronizerId");
	}
	
	public long getJobStartBlock() {
		return this.config.getLong("job.startBlock");
	}
	
	public long getJobStopBlock() {
		return this.config.getLong("job.stopBlock");
	}

	public ImportJobConfig getImportJobConfig() {
		return new ImportJobConfig(this.config.getConfig("job.import"));
	}
	
	public JobConfig getJobConfig(String name) {
		return new JobConfig(this.config.getConfig("job."+name));
	}
	
	public AccountStoreConfig getAccountStoreConfig() {
		return new AccountStoreConfig(this.config.getConfig("job.accountstore"));
	}
	
	public static class AccountStoreConfig{
		
		private Config config;
		
		public AccountStoreConfig(Config config) {
			this.config = config;
		}
		
		public String getDBPath() {
			return this.config.getString("dbpath");
		}
		
		public int getFlushSize() {
			return this.config.getInt("flushSize");
		}
		
	}
	
	public static class ImportJobConfig{
		
		private Config config;

		public ImportJobConfig(Config config) {
			this.config = config;
		}
		
		public int getBatchSize() {
			return this.config.getInt("batchSize");
		}
		
		public int getWorkers() {
			return this.config.getInt("workers");
		}
		
		public boolean isEnabled() {
			return this.config.getBoolean("enabled");
		}
		
	}
	
	public static class JobConfig{
		
		private Config config;

		public JobConfig(Config config) {
			this.config = config;
		}
		
		public int getBatchSize() {
			return this.config.getInt("batchSize");
		}
		
		public int getWorkers() {
			return this.config.getInt("workers");
		}
		
		public int getWorkerBatchSize() {
			return this.config.getInt("workerBatchSize");
		}
		
		public long getTimeout() {
			return this.config.getLong("timeout");
		}
		
		public boolean isEnabled() {
			return this.config.getBoolean("enabled");
		}
		
		public boolean getBoolean(String key) {
			return this.config.getBoolean(key);
		}
		
		public List<Integer> getIntList(String key){
			return this.config.getIntList(key);
		}
		
		
		
	}	
	 
	
}
