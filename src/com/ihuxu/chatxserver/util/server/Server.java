package com.ihuxu.chatxserver.util.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
				/** listening to the new socket **/
				Socket socket = serverSocket.accept();

				/** client thread **/
				System.out.println("the recieved the serverSocket.");
				try {
					ClientThread clientThread = new ClientThread(socket);
					if(ClientThreadManager.addClientThread(clientThread.getClientKey(), clientThread)) {
						clientThread.writeLoginMessagePackage(MessagePackage.TYPE_LOGIN_SUC_MSG);
						clientThread.start();
					} else {
						clientThread.writeLoginMessagePackage(MessagePackage.TYPE_LOGIN_FAI_MSG);
						clientThread.close();
						clientThread = null;
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
