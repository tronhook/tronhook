# TRONHOOK

> Get the data that you need for your dApp

![TronHook architecture and features](https://raw.githubusercontent.com/tronhook/docs/master/images/tronhook.png)

TronHook is an extensible and scalable data processor for Tron blockchain with a builtin rule engine.

If you are creating a dApp based on Tron or simply need to get some specific data from the blockchain, TronHook can help you to get the data that you need without constantly polling data from explorers or Tron API or having to do your own blockchain processor from scratch. 

It aims to simplify data integration between Tron and (d)Apps with a publish/subscribe model: you declare what data you are expecting with rules, and TronHook will push that data directly to your dApp.

Some usecases TronHook can help with:

- ETL: extract specific data matching specific rules in order to analyze them later
- Track all the transactions of a specific address, token etc ...
- Receive notification (via websocket or http) on specific blockchain events : like vote for an address, specific contract trigger
- Confirm that a transaction is confirmed :]

You can  [have a look at the demo here](https://tronhook.github.io/demo) / [Check the documentation here](https://tronhook.github.io/docs/)