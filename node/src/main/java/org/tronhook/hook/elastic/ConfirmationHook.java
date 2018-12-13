package org.tronhook.hook.elastic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.tronhook.api.TronBlockHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.parser.BlockParser;

import com.typesafe.config.Config;

import io.trxplorer.troncli.TronSolidityNodeCli;

/**
 * Confirms blocks and transactions
 *
 */
public class ConfirmationHook extends TronBlockHook{

	public static final String TRON_HOOK_ID = "ConfirmationES";
	
	private TronSolidityNodeCli solidityCli;
	
	private RestHighLevelClient client;
	
	public ConfirmationHook(Config config) {
		super(config);
	}

	@Override
	public void onNodeStart() {
		
		this.client = new RestHighLevelClient(RestClient.builder(new HttpHost("151.80.41.202", 9200, "http"),
				new HttpHost("151.80.41.202", 9201, "http")));
		
		this.solidityCli = new TronSolidityNodeCli("127.0.0.1:50051",true);
		
	}

	@Override
	public void onNodeStop() {

		try {
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {
		
		List<Long> nums = new ArrayList<>();
			HashMap<Long, BlockModel> fullBlocks = new HashMap<>();
		for(BlockModel b:blocks) {
			fullBlocks.put(b.getHeight(), b);

			if (b.getHeight()<=getBlockInfo().getLastSolidityBlock()) {
				nums.add(b.getHeight());
			}else {
				getBlockInfo().addUnprocessedBlock(b.getHeight());
				getBlockInfo().addRemoveTry(b.getHeight());
			}
			
		}
		

		
		try {

			List<BlockModel> solidityBlocks = BlockParser.parseBlocks(this.solidityCli.getBlocksByNums(nums));
			
			BulkRequest bulkRequest = new BulkRequest();
			//confirm blocks
			for(BlockModel sBlock:solidityBlocks) {
				boolean confirmed = false;
				if (fullBlocks.get(sBlock.getHeight())!=null) {
					confirmed = true;
				}

				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("confirmed", confirmed);


				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("blocks", "blocks",
						String.valueOf(sBlock.getHeight())).source(builder);

				bulkRequest.add(indexRequest);

				//update transactions matching this block
				UpdateByQueryRequest request = new UpdateByQueryRequest("transactions"); 
				request.setQuery(new TermQueryBuilder("block", sBlock.getHeight()));
				request.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source.confirmed=true", Collections.emptyMap()));
				
				client.updateByQueryAsync(request, RequestOptions.DEFAULT, new ActionListener<BulkByScrollResponse>() {
					
					@Override
					public void onResponse(BulkByScrollResponse response) {
						
						System.out.println("update tx ok");
						
					}
					
					@Override
					public void onFailure(Exception e) {
						System.out.println("update tx ko");
						
					}
				});
				
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

}
