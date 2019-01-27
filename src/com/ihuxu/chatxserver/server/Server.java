package com.ihuxu.chatxserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ihuxu.chatxserver.client.Client;
import com.ihuxu.chatxserver.client.ClientLoginPool;
import com.ihuxu.chatxserver.common.model.MessagePackage;


public class Server {

	private ServerSocket serverSocket;

	public Server() {}

	public void start() {
		System.out.println("Server starting...");
		/** crontab **/
		ServerCrontab.checkClientSocket();

		/** listening **/
		try {
			serverSocket = new ServerSocket(1722);
			boolean go = true;
			while (go) {
			    try {
			        /** listening to the new socket **/
			        Socket socket = serverSocket.accept();

			        /** client thread **/
			        System.out.println("the recieved the serverSocket.");
			        Client client = new Client(socket);
			        try {
			            ClientLoginPool.getInstance().offer(client);
			        } catch (Exception e) {
			            // Server is full, try later.
			            System.err.println("Server is full, try later.");
			            client = null;
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
	}

	public void restart() {
	}

}
