# TRONHOOK

TronHook is an extensible and scalable block/data processor for Tron blockchain

Some usecases TRONHook can help with:
- ETL: extract specific data matching specific rules in order to analyze them later
- Track a specific address or token
- Receive notification (via websocket or http) on specific events : like vote for an address, specific contract trigger
- Confirm that a transaction is confirmed :]

# Hook
A hook is simply a task that will be executed each time a block is met on TRON blockchain. This block can be a new block or a block for which the transactions matches a rule or a set of rules you defined.

# Node
A node is the engine that lets you run a hook. When you run a node you have to specify the hook to use and it's configuration.

A node can run a single hook, but you can run as many node as you want on a single or multiple servers.

# Available Hooks
TRONHook comes with some default hooks that you can use straight away

## BlockSyncHook 

Synchronizes the blocks into an elasticsearch database

- Configuration to add in application.[env].conf
```
hook="org.tronhook.hook.elastic.BlockSyncHook"

BlockSyncES{
url=<ELASTIC_SEARCH_URL>
}

```
## AccountSyncHook
Synchronizes the accounts into an elasticsearch database

## NotificationHook

## ConfirmationHook

# Rules

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

nodeId=1 #useful if you want to start multiple nodes to process the same hook in parell

#Specify the qualified name of the Hook to use
hook = "org.tronhook.XXX" # For example: org.tronhook.hook.elastic.BlockSyncHook


blockStart=0 # The block from which this node should start processing
blockStop=-1 # The block to which this node should stop processing (-1 means until last available blocks)
blockRefBatchSize=100000 #Block reference batch size

tron{
	fullnode="<FULL_NODE_HOST>"
	soliditynode="<SOLIDITY_NODE_HOST>"
	mainNet=true
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
docker run -p 7171:7171 --name tronhook.blocks --restart unless-stopped --log-opt max-size=50m -d -v application.prod.conf:/application.prod.conf -e APP_OPTS="prod" tronhook/node
```
