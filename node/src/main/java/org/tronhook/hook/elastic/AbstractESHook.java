package org.tronhook.hook.elastic;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tronhook.api.TronHook;

import com.typesafe.config.Config;

public abstract class AbstractESHook extends TronHook{

	protected RestHighLevelClient client;
	
	public AbstractESHook(Config config) {
		super(config);
	}

	
	
	protected void processBulkRequest(BulkRequest bulkRequest,String type) throws IOException {
		
		
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
