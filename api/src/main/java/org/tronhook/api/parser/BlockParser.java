package org.tronhook.api.parser;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.tron.common.utils.Sha256Hash;
import org.tron.core.Wallet;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.protos.Protocol.Block;
import org.tron.protos.Protocol.Transaction;
import org.tron.protos.Protocol.Transaction.Contract;
import org.tronhook.api.model.BlockModel;
import org.tronhook.api.model.TransactionModel;

public class BlockParser {

	public static List<BlockModel> parseBlocks(List<Block> rawBlocks,boolean confirmed) throws BlockParserException{
		
		ArrayList<BlockModel> result = new ArrayList<>();
		
		for(Block rawBlock:rawBlocks) {
			result.add(parseBlock(rawBlock,confirmed));
		}
		
		return result;
	}
	
	public static BlockModel parseBlock(Block rawBlock,boolean confirmed) throws BlockParserException{
		
		try {
		   long blockNum = rawBlock.getBlockHeader().getRawData().getNumber();
		   
		   long timestamp = rawBlock.getBlockHeader().getRawData().getTimestamp();
		   int txCount = rawBlock.getTransactionsCount();
		   String witnessAddress = Wallet.encode58Check(rawBlock.getBlockHeader().getRawData().getWitnessAddress().toByteArray());
		   String parentHash = Sha256Hash.wrap(rawBlock.getBlockHeader().getRawData().getParentHash()).toString();
		   int blockSize = rawBlock.getSerializedSize();
		   String blockHash =  Sha256Hash.of(rawBlock.getBlockHeader().getRawData().toByteArray()).toString();
		   
		   
		   BlockModel block = new BlockModel();
		   
		   block.setHeight(blockNum);
		   block.setHash(blockHash);
		   block.setParentHash(parentHash);
		   block.setWitness(witnessAddress);
		   block.setTxCount(txCount);
		   block.setSize(blockSize);
		   block.setTimestamp(timestamp);
		   block.setConfirmed(confirmed);
		   
			for (Transaction rawTransaction : rawBlock.getTransactionsList()) {

				for (Contract rawContract : rawTransaction.getRawData().getContractList()) {
					
					TransactionModel transaction = new TransactionModel();
					
					byte[] from = TransactionCapsule.getOwner(rawContract);
					byte[] to = TransactionCapsule.getToAddress(rawContract);
					String fromAddress = from!=null ? Wallet.encode58Check(from) : null;
					String toAddress = to!=null ? Wallet.encode58Check(to): null;
					int txSize = rawTransaction.getRawData().getSerializedSize();
					
					String txHash = Sha256Hash.of(rawTransaction.getRawData().toByteArray()).toString();
					int txType = rawContract.getTypeValue();						
					
					if (!StringUtils.isBlank(rawTransaction.getRawData().getData().toStringUtf8())) {
						transaction.setData(rawTransaction.getRawData().getData().toStringUtf8());
					}
					
					transaction.setTimestamp(timestamp);
					transaction.setFrom(fromAddress);
					transaction.setTo(toAddress);
					transaction.setHash(txHash);
					transaction.setType(txType);
					transaction.setBlock(blockNum);
					transaction.setSize(txSize);
					transaction.setContract(ContractParser.parse(rawContract));
					transaction.setConfirmed(confirmed);
					
					block.addTransaction(transaction);
				}

			}
			 
			return block;
		}catch(Exception e) {
			throw new BlockParserException("Could not parse block ", e);
		}
		
		
	}
	
}
