package org.tronhook.hook.elastic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.tron.protos.Protocol.TransactionInfo;
import org.tronhook.api.NodeType;
import org.tronhook.api.TronHookException;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;

import io.trxplorer.troncli.TronSolidityNodeCli;

public class BlockSyncHook extends AbstractESHook {

	public static final String TRON_HOOK_ID = "BlockSyncES";

	private TronSolidityNodeCli tronSolidityCli;

	public BlockSyncHook(Config config) {
		super(config);
	}

	@Override
	public void processBlocks(List<BlockModel> blocks) throws TronHookException {

		try {
			BulkRequest bulkRequest = new BulkRequest();

			for (BlockModel block : blocks) {

				getLogger().info("=>Block: {}", block.getHeight());

				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("height", block.getHeight());
					builder.field("txCount",block.getTxCount());
					builder.field("parentHash",block.getParentHash());
					builder.field("size",block.getSize());
					builder.field("witness",block.getWitness());
					
					builder.timeField("timestamp", block.getTimestamp());
					if (getNodeType().equals(NodeType.SOLIDITY)) {
						builder.field("confirmed", true);
					}
				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("blocks", "blocks", String.valueOf(block.getHeight()))
						.source(builder);

				bulkRequest.add(indexRequest);

			}

			processBulkRequest(bulkRequest, "block");
			
			List<TransactionModel> transactions = getAllTransactions(blocks);
			
			processTransactions(transactions);
			
			boolean trackAccounts = getConfig().getBoolean("trackAccounts");
			
			if (trackAccounts) {
				processAccounts(transactions);				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void processAccounts(List<TransactionModel> transactions) {
		
		HashMap<String,Long> addressesByBlock = new HashMap<>();
		
		for(TransactionModel tx:transactions) {
				
			if (tx.getFrom()!=null && addressesByBlock.get(tx.getFrom())==null) {
				addressesByBlock.put(tx.getFrom(), tx.getBlock());
			}else if (tx.getFrom()!=null && addressesByBlock.get(tx.getFrom())!=null) {
				long previousBlock  = addressesByBlock.get(tx.getFrom());
				if (tx.getBlock()>previousBlock) {
					addressesByBlock.put(tx.getFrom(), tx.getBlock());	
				}
			}
			
			if (tx.getTo()!=null && addressesByBlock.get(tx.getTo())==null) {
				addressesByBlock.put(tx.getTo(), tx.getBlock());
			}else if (tx.getTo()!=null && addressesByBlock.get(tx.getTo())!=null) {
				long previousBlock  = addressesByBlock.get(tx.getTo());
				if (tx.getBlock()>previousBlock) {
					addressesByBlock.put(tx.getTo(), tx.getBlock());	
				}
			}
			

		}
		
		
		try {
			BulkRequest bulkRequest = new BulkRequest();

			for (String address : addressesByBlock.keySet()) {

				XContentBuilder builder = XContentFactory.jsonBuilder();
				builder.startObject();
				{
					builder.field("block", addressesByBlock.get(address));
				}
				builder.endObject();

				IndexRequest indexRequest = new IndexRequest("accounts", "accounts",
						address).source(builder);

				bulkRequest.add(indexRequest);

			}

			processBulkRequest(bulkRequest, "account");

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	public void processTransactions(List<TransactionModel> transactions) {

		List<String> txIds = transactions.stream().map((tx) -> tx.getHash()).collect(Collectors.toList());

		boolean fetchFee = getConfig().getBoolean("fetchFee");
		boolean storeContract= getConfig().getBoolean("contract");
				
		Map<String, TransactionInfo> txInfos = new HashMap<>();

		if (fetchFee && getNodeType().equals(NodeType.SOLIDITY)) {
			txInfos = tronSolidityCli.getTxInfosByHash(txIds);
		}

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

					if (getNodeType().equals(NodeType.SOLIDITY)) {
						builder.field("confirmed", true);

						if (fetchFee) {
							TransactionInfo txInfo = this.tronSolidityCli.getTxInfoByHash(tx.getHash());

							HashMap<String, Long> fee = new HashMap<>();
							fee.put("netUsage", txInfo.getReceipt().getNetUsage());
							fee.put("netFee", txInfo.getReceipt().getNetFee());
							fee.put("energyUsage", txInfo.getReceipt().getEnergyUsage());
							fee.put("energyFee", txInfo.getReceipt().getEnergyFee());
							builder.field("receipt", fee);
						}

					}

					if (storeContract) {
						ObjectMapper om = new ObjectMapper();
						builder.field("contract", om.convertValue(tx.getContract(), Map.class));						
					}


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

	@Override
	public void onNodeStart() {
		super.onNodeStart();

		this.tronSolidityCli = new TronSolidityNodeCli(this.getConfig().getString("soliditynode"), true);

	}

}
