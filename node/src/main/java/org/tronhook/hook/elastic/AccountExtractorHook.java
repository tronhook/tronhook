package org.tronhook.hook.elastic;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import org.tron.protos.Protocol.Account;
import org.tronhook.api.TronBlockHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.typesafe.config.Config;

import io.trxplorer.troncli.TronFullNodeCli;

public class AccountExtractorHook extends TronBlockHook {

	public static final String TRON_HOOK_ID = "AccountSyncES";
	private RestHighLevelClient client;
	private TronFullNodeCli fullCli;
	
	public AccountExtractorHook(Config config) {
		super(config);
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {

		HashSet<String> addresses = new HashSet<>();

		for (BlockModel b : blocks) {
			addresses.add(b.getWitness());

			for (TransactionModel tx : b.getTransactions()) {
				if (StringUtils.isNotBlank(tx.getFrom())) {
					addresses.add(tx.getFrom());
				}
				if (StringUtils.isNotBlank(tx.getTo())) {
					addresses.add(tx.getTo());
				}
			}
		}

		try {

			BulkRequest bulkRequest = new BulkRequest();
			//System.out.println("address count:"+addresses.size());
			//this.fullCli.getAccountsByAddresses(addresses);
			//System.out.println("address count fetched:"+addresses.size());
			for (String address : addresses) {

				//Account account = this.fullCli.getAccountByAddress(address);
				
				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("address", address);
					//builder.field("balance", account.getBalance());

				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("accounts", "accounts", String.valueOf(address))
						.source(builder);

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
		
		this.fullCli = new TronFullNodeCli("47.88.174.175:50051",true);
	}

	@Override
	public void onNodeStop() {

		System.out.println("NODE STOP");

	}

}
