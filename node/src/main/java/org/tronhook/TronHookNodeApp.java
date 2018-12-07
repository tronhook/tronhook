package org.tronhook;

import org.jongo.Jongo;
import org.jooby.Jooby;
import org.jooby.mongodb.Jongoby;
import org.jooby.mongodb.Mongodb;
import org.jooby.quartz.Quartz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tronhook.job.BlockRefJob;
import org.tronhook.job.LastBlockProcessorJob;

import io.trxplorer.troncli.TronFullNodeCli;
import io.trxplorer.troncli.TronSolidityNodeCli;


public class TronHookNodeApp extends Jooby {
	
	private static final Logger logger = LoggerFactory.getLogger(TronHookNodeApp.class);
	
	{
		use(new Mongodb());
		use(new Jongoby());
		use(new TronHookNodeModule());
	
		
		use(new Quartz(BlockRefJob.class,
				LastBlockProcessorJob.class
				));

		
		onStart((registry)->{

			Jongo jongo = registry.require(Jongo.class);
			
			//jongo.getCollection("blocks").ensureIndex("{number:1}","{unique:true}");
			jongo.getCollection("blocks").ensureIndex("{tries:1}");
			
		});
		
		onStop((registry)->{

			TronFullNodeCli fullCli = registry.require(TronFullNodeCli.class);
			fullCli.shutdown();
			
			TronSolidityNodeCli solidityCli = registry.require(TronSolidityNodeCli.class);
			solidityCli.shutdown();

			
		});
		

		get("/", (req, res) -> {

			res.send("");
		});

	}

	public static void main(final String[] args) {
		run(TronHookNodeApp::new, args);
	}

}