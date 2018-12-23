# TRONHOOK

TronHook is an extensible and scalable data processor for Tron blockchain backed with a rule engine.

If you are creating a Dapp based on Tron or simply need to get some specific data, TronHook can help you to get the data that you need without constantly polling data from explorers API or having to do your own blockchain processor from scratch.

Some usecases TronHook can help with:

- ETL: extract specific data matching specific rules in order to analyze them later
- Track a specific address or token
- Receive notification (via websocket or http) on specific events : like vote for an address, specific contract trigger
- Confirm that a transaction is confirmed :]

# Hook
A hook is a task that will be executed each time a block is met on TRON blockchain. This block can be a new block or a block for which the transactions matches a rule or a set of rules you defined.

# Node
The node is the engine that lets you run a hook. When you run a node you have to specify the hook to use and it's configuration.

!> A node can run a single hook, but you can run as many node as you want on a single or multiple servers.

# Rules
TronHook comes with a rule engine that allows you to filter some blocks and or transactions so that your hook can process only the data that you need.

Rules can be added dynamically at runtime through TronHook node's API without any restart required.

Here are some examples of rules:

**Match all  transaction of type "TransferContract" **
```bash
curl -X POST "localhost:7171/rule" -H 'Content-Type: application/json' -d'
{
	"id":"contract_trigger",
	"rule":"type==1",
	"context":"transaction",
	"repeat":"always"
}
'
```
**Match a  transaction once with specific hash and tx is confirmed**
```bash
curl -X POST "localhost:7171/rule" -H 'Content-Type: application/json' -d'
{
	"id":"contract_trigger",
	"rule":"hash=='5a2e60fe45906668fa59fd9f9d414209106f51fd52ac7176104bc0b31c66dfff' and confirmed==true",
	"context":"transaction",
	"repeat":"once"
}
'
```

**Match all  transactions of type "TriggerContract" with a  specific contract address**
```bash
curl -X POST "localhost:7171/rule" -H 'Content-Type: application/json' -d'
{
	"id":"contract_trigger",
	"rule":"tx.type==31 and contract.contractAddress=='TEEXEWrkMFKapSMJ6mErg39ELFKDqEs6w3'",
	"context":"transaction",
	"repeat":"always"
}
'
```

## Rule specification
Field | Description
-------- | -----
id | Rule unique id
rule | Rule expression
context | [transaction\|block] indicates on which type of fact (block or transaction) should this rule be applied
repeat | [once\|forever] if "once" the rule will be removed the first time it matches a fact

## Rule expression




# Available Hooks
TRONHook comes with some default hooks that you can use straight away

## BlockSyncHook 

Synchronizes the blocks into an elasticsearch database

**Configuration to add in application.[env].conf**
```
hook="org.tronhook.hook.elastic.BlockSyncHook"

BlockSyncES{
	soliditynode=${tron.soliditynode} #solidity node to use (only used if node type is set to solidity)
	fetchFee=false #should tx fees be retrieved while processing transactions ? (only used if node type is set to solidity)
	elasticsearch{
		host="<HOST>"
		port=<PORT>
		scheme=[http|https]
	}
}

```
## AccountSyncHook
Synchronizes the accounts into an elasticsearch database

## NotificationHook

NotificationHook lets you receive notifications (via HTTP or WS) when the rules have matching facts (block or transactions)

- http notification: you must provide a callback url that will be use to make a POST request with all the rules that were 'true' as well as the associated facts data (blocks or transactions)

**Configuration to add in application.[env].conf**
```
hook="org.tronhook.hook.NotificationHook"

NotificationHook{
	httpcallbackurl="<http_url>"
}
```

**Sample of notification body**
The notification request body is a json payload structured as followed:

```json
{
'ruleId':[list of facts matching this rule]
}
```
Example:

```json
{
    "contract_trigger": [
        {
            "hash": "97f2947ca4b1c0f688625cf35035d21a9387ed84b1951bd0b8375d3beb29b8d1",
            "block": 5068245,
            "contract": {
                "from": "TSV5n6u5CcaT2K1c6WbWuqBZXDgUddnn9N",
                "contractAddress": "TEEXEWrkMFKapSMJ6mErg39ELFKDqEs6w3",
                "callValue": 0,
                "data": null,
                "callTokenValue": 45000000,
                "tokenId": 0
            },
            "from": "TSV5n6u5CcaT2K1c6WbWuqBZXDgUddnn9N",
            "to": null,
            "timestamp": 1545206664000,
            "type": 31,
            "data": null,
            "size": 216
        }
    ]
}
```

# Starting a node
Hooks can be launched with tronhook-node that will take care of running the hooks only on the blocks that haven't been processed yet or on the blocks or transactions that match some specific rules that you have defined.

## Configuration file

 Create a basic configuration file where you will be able to specify the hooks to run and it's configuration:

```

#Configuration

application {
 tz="UTC"
 port=7171
 env=prod
 name=tronhook_${application.env}
 baseUrl ="http://localhost:"${application.port}
}

db = <mongodburl> # mongodb connection url

#Tron nodes configuration
tron{
	fullnode="<FULL_NODE_HOST>"
	soliditynode="<SOLIDITY_NODE_HOST>"
	mainNet=true
}

nodeId=1 #useful if you want to start multiple nodes to process the same hook in parell

#Specify the qualified name of the Hook to use
hook = "org.tronhook.XXX" # For example: org.tronhook.hook.elastic.BlockSyncHook


blockStart=0 # The block from which this node should start processing
blockStop=-1 # The block to which this node should stop processing (-1 means until last available blocks)
blockRefBatchSize=100000 #Block reference batch size
node=[full|solidity] # node type to use (full or solidity)
#Latest block processor configuration
latestBlocks{
	enabled=true
	batchSize=1
	workers=1
	workerBatchSize=1
}
#Previous block processor configuration
previousBlocks{
	enabled=true
	batchSize=1000
	workers=10
	workerBatchSize=100	
}


```

## Launching the node with Docker

Tronhook requires a mongodb instance in order to keep a record of the blocks already processed.

First start a mongodb instance:
```
docker run --name mongo -p 27017:27017 -d mongo
```
Set the url of this mongo instance in your configuration file:
```
db=<mongodburl>
```
Once that you have created your configuration file you can launch a node like this:
```
docker run -p 7171:7171 --name tronhook.blocks -d -v application.prod.conf:/application.prod.conf -e APP_OPTS="prod" tronhook/node
```

# Writing your own Hook
TODO
