package org.tronhook.hook;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.tronhook.api.TronHook;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.hook.notification.NotificationWSServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.typesafe.config.Config;

public class NotificationHook extends TronHook{

	public static final String TRON_HOOK_ID = "NotificationHook";
	
	private NotificationWSServer wsServer;
	
	public NotificationHook(Config config) {
		super(config);
		
	}

	@Override
	public void onNodeStart() {
		
		Unirest.setObjectMapper(new ObjectMapper() {
		    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
		                = new com.fasterxml.jackson.databind.ObjectMapper();

		    public <T> T readValue(String value, Class<T> valueType) {
		        try {
		            return jacksonObjectMapper.readValue(value, valueType);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }

		    public String writeValue(Object value) {
		        try {
		            return jacksonObjectMapper.writeValueAsString(value);
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		});
		
		if (this.getConfig().hasPath("protocol") && this.getConfig().getString("protocol").equals("ws")) {
			
			String host= this.getConfig().getString("ws.host");
			int port = this.getConfig().getInt("ws.port");
			
			this.wsServer = new NotificationWSServer(host, port);
			
			//run ws server asynchronously: non blocking
			CompletableFuture.runAsync(this.wsServer);
		}

		
	}

	@Override
	public void onNodeStop() {
		
		try {
			this.wsServer.stop();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {

				
			Map<String, List<Object>> rules = getMatchingRules();
			
			String protocol = "http";
			
			if (this.getConfig().hasPath("protocol")) {
				protocol = this.getConfig().getString("protocol");
			}
			
			if (protocol.equals("ws")) {
				
				if (!rules.isEmpty()) {

					com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();

					try {
						String payload = om.writeValueAsString(rules);

						this.wsServer.broadcast(payload);
					
					} catch (JsonProcessingException e) {
						
						e.printStackTrace();
						
						throw new TronHookException("Could not broadcast", e);
						
					}
					
				}

				
			}else {

				if (!rules.isEmpty() && getConfig().hasPath("httpcallbackurl")) {
					try {
						Unirest.post(getConfig().getString("httpcallbackurl"))
				        	.header("accept", "application/json")
				        	.header("Content-Type", "application/json")
				            .body(getMatchingRules())
						  .asString();
					} catch (UnirestException e) {

						throw new TronHookException("Could not invoke url callback", e);
					}				
				}
				
			}
			


		
	}


	
	
	
}
