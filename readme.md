# TRONHOOK

> Get the data that you need for your dApp

![TronHook architecture and features](https://raw.githubusercontent.com/tronhook/docs/master/images/tronhook.png)

TronHook is an extensible and scalable data processor for Tron blockchain with a builtin rule engine.

If you are creating a dApp based on Tron or simply need to get some specific data from the blockchain, TronHook can help you to get the data that you need without constantly polling data from explorers or Tron API or having to do your own blockchain processor from scratch. 

It aims to simplify data integration between Tron and (d)Apps with a publish/subscribe model: you declare what data you are expecting with rules, and TronHook will push that data directly to your dApp, a database or anywhere you want.

Some usecases TronHook can help with:

- ETL: extract specific data matching specific rules in order to analyze them later
- Track all the transactions of a specific address, token etc ...
- Receive notification (via websocket or http) on specific blockchain events : like vote for an address, specific contract trigger
- Confirm that a transaction is confirmed :]


**Demo:** [https://tronhook.github.io/demo](https://tronhook.github.io/demo)

**Documentation:**  [https://tronhook.github.io/docs/](https://tronhook.github.io/docs/)

**What difference with TronGrid ?**

TronGrid currently relies on a modified version of Tron node (java-tron) in order to save the events from smart contracts directly into a database. TronGrid provides an API to query those events. It also provides an API to query blocks and transactions but that is only a load-balancer for Tron nodes grpc/http interfaces, it doesn't store or process any data from blocks.

When running TronHook you don't have to run a modified node, it fetches only the data that you need directly from Tron's (non modified) nodes.

TronHook is a more flexible alternative to TronGrid because it can be customized to your very specific needs and requirements.

TronGrid currently only provides data about smart contract events whereas TronHook can currently be used to provide dApps with events and data other than the smartcontract ones (transfer, vote, exchange, etc..) via websocket or http, but it can also be used as a customizable ETL for Tron blockchain.