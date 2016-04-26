package com.ihuxu.chatx.common.server;

import java.net.Socket;

public class ClientThread extends Thread{
	
	private Socket socket;
	
	public ClientThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("client server thread running...");
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
}
