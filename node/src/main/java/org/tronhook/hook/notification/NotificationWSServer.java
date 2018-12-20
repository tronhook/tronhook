package org.tronhook.hook.notification;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class NotificationWSServer extends WebSocketServer{
	
	public NotificationWSServer(String host,int port) {
		super(new InetSocketAddress(host, port));
	}
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {

		
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {

		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {

		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {

		
	}

	@Override
	public void onStart() {
		System.out.println("WS server started");
		
	}

}
