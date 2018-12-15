package org.tronhook.hook.elastic;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tronhook.api.TronHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;

public class BlockSyncHook extends TronHook{

	public static final String TRON_HOOK_ID = "BlockSyncES";
	private RestHighLevelClient client;

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
	
	private void processBulkRequest(BulkRequest bulkRequest,String type) throws IOException {
		
		
		if (bulkRequest.requests().size() > 0) {

			client.bulkAsync(bulkRequest, RequestOptions.DEFAULT,new ActionListener<BulkResponse>() {
				
				@Override
				public void onResponse(BulkResponse response) {

					if (response.hasFailures()) {
						
						StreamSupport.stream(response.spliterator(), false).filter((r)->r.isFailed()).forEach((bulkItemResponse)->{
							
							BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
							getLogger().error("ES failure on block {} : {}",bulkItemResponse.getId(),failure.getMessage(),failure.getCause());							
							//TODO replay block
						});
						


					}else {
						List<String> ids = StreamSupport.stream(response.spliterator(), false).map((r)->r.getId()).collect(Collectors.toList());
						getLogger().info("{} {} ingested",ids.size(),type);
						getLogger().debug(type+" ingested: [{}]",ids);
						//TODO mark as done
					}
					
				}
				
				@Override
				public void onFailure(Exception e) {
					getLogger().error("Could not process bulk request",e);
					//TODO replay all blocks from bulk
				}
			});
			
	
			
		}
	}
	
	@Override
	public void onNodeStart() {

		this.client = new RestHighLevelClient(RestClient.builder(new HttpHost(getConfig().getString("elasticsearch.host"), getConfig().getInt("elasticsearch.port"), getConfig().getString("elasticsearch.scheme"))));

	}

	@Override
	public void onNodeStop() {
		
		try {
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}

}
