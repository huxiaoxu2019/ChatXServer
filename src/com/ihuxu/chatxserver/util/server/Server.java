package com.ihuxu.chatxserver.util.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	private ServerSocket serverSocket;

	public Server() {
	}

	public void start() {
		System.out.println("Server starting...");
		/** crontab **/
		ServerCrontab.checkClientSocket();

		/** listening **/
		try {
			serverSocket = new ServerSocket(1720);
			Socket socket;
			boolean go = true;
			while (go) {
				/** listening to the new socket **/
				socket = serverSocket.accept();

				/** client thread **/
				System.out.println("the recieved the serverSocket.");
				ClientThread clientThread = new ClientThread(socket);
				ClientThreadManager.addClientThread(clientThread.getClientKey(), clientThread);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
	}

	public void restart() {
	}

}
