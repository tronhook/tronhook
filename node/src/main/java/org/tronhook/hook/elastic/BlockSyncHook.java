package org.tronhook.hook.elastic;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;

public class BlockSyncHook extends AbstractESHook{

	public static final String TRON_HOOK_ID = "BlockSyncES";


	public BlockSyncHook(Config config) {
		super(config);
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {

		try {
			BulkRequest bulkRequest = new BulkRequest();

			for (BlockModel block : blocks) {

				getLogger().info("=>Block: {}",block.getHeight());
				
				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("hash", block.getHash());
					builder.field("height", block.getHeight());
					builder.timeField("timestamp", block.getTimestamp());

				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("blocks", "blocks", String.valueOf(block.getHeight()))
						.source(builder);

				bulkRequest.add(indexRequest);
				
				
				
				
			}

			processBulkRequest(bulkRequest,"block");
			
			processTransactions(getAllTransactions(blocks));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void processTransactions(List<TransactionModel> transactions) {

		try {
			BulkRequest bulkRequest = new BulkRequest();

			for (TransactionModel tx : transactions) {


				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("hash", tx.getHash());
					builder.field("type", tx.getType());
					builder.field("from", tx.getFrom());
					builder.field("to", tx.getTo());
					builder.field("block", tx.getBlock());
					builder.timeField("timestamp", tx.getTimestamp());
					
					ObjectMapper om = new ObjectMapper();

					builder.field("contract", om.convertValue(tx.getContract(), Map.class));

				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("transactions", "transactions",
						String.valueOf(tx.getHash())).source(builder);

				bulkRequest.add(indexRequest);
				
			
				
			}

			processBulkRequest(bulkRequest, "tx");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
}
