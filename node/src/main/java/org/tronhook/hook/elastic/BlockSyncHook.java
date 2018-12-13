package org.tronhook.hook.elastic;

import java.util.List;
import java.util.Map;

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
import org.tronhook.api.ITronBlockHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.TronTransactionHook;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;

public class BlockSyncHook extends TronTransactionHook implements ITronBlockHook {

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
System.out.println("=>"+block.getHeight());
				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("hash", block.getHash());
					builder.field("height", block.getHeight());


				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("blocks", "blocks", String.valueOf(block.getHeight()))
						.source(builder);

				bulkRequest.add(indexRequest);

			}

			if (bulkRequest.requests().size() > 0) {

				client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {

					@Override
					public void onResponse(BulkResponse response) {

						if (response.hasFailures()) {
							System.out.println("block bulk async response => FAILURES");
							for (BulkItemResponse bulkItemResponse : response) {
								if (bulkItemResponse.isFailed()) {
									BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
									System.out.println(failure.getMessage());
								}
							}

						} else {
							System.out.println("block bulk async response => OK");
						}

					}

					@Override
					public void onFailure(Exception e) {

						System.out.println("bulk async FAILURE");
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
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

					ObjectMapper om = new ObjectMapper();

					builder.field("contract", om.convertValue(tx.getContract(), Map.class));

				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("transactions", "transactions",
						String.valueOf(tx.getHash())).source(builder);

				bulkRequest.add(indexRequest);

			}

			if (bulkRequest.requests().size() > 0) {

				client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {

					@Override
					public void onResponse(BulkResponse response) {

						if (response.hasFailures()) {
							System.out.println("bulk async response => FAILURES");
							for (BulkItemResponse bulkItemResponse : response) {
								if (bulkItemResponse.isFailed()) {
									BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
									System.out.println(failure.getMessage());
								}
							}

						} else {
							System.out.println("bulk async response => OK");
						}

					}

					@Override
					public void onFailure(Exception e) {

						System.out.println("bulk async FAILURE");
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onNodeStart() {

		this.client = new RestHighLevelClient(RestClient.builder(new HttpHost("151.80.41.202", 9200, "http"),
				new HttpHost("151.80.41.202", 9201, "http")));

	}

	@Override
	public void onNodeStop() {

		System.out.println("NODE STOP");

	}

}
