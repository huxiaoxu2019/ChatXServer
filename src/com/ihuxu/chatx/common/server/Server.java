package com.ihuxu.chatx.common.server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.ihuxu.chatx.common.model.MessageManager;

public class Server {
	
	private ServerSocket serverSocket;

	public Server() {
		boolean go = true;
		while(go) {
			try{
				serverSocket = new ServerSocket(1720);
				
				/** listening to the new socket **/
				Socket socket = serverSocket.accept();
				
				/** client thread **/
				ClientThread clientThread = new ClientThread(socket);
				ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
				MessageManager mM = (MessageManager) oIS.readObject();
				ClientThreadManager.addClientThread(mM.getClientKey(), clientThread);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				go = false;
			}
		}
	}

}
