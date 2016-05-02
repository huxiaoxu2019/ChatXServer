package com.ihuxu.chatxserver.util.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ihuxu.chatxserver.common.model.MessageManager;

public class ClientThread extends Thread{
	
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private MessageManager lastMessageManager;
	private boolean listened = true;
	private String clientKey;
	
	public ClientThread(Socket socket) throws Exception {
		super();
		this.socket = socket;
		this.clientKey = this.getClientKey();
	}
	
	public void run() {
		System.out.println("client server thread running...");
			while(this.listened) {
				try {
					System.out.println("client server thread listening...");
					this.lastMessageManager = (MessageManager) this.getObjectInputStream().readObject();
					System.out.println("recieved one object from client -> " + this.getClientKey());
					if(this.lastMessageManager.hasTextMessage()) {
						System.out.println("uid:" + this.lastMessageManager.getTextMessage().get("uid"));
					}
				} catch (IOException e) {
					e.printStackTrace();
					this.setListened(false);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	public boolean isListened() {
		return listened;
	}

	public void setListened(boolean listened) {
		this.listened = listened;
	}

	public Socket getSocket() {
		return this.socket;
	}
	
	public String getClientKey() throws Exception {
		if(this.clientKey == null) {
			this.lastMessageManager = (MessageManager) this.getObjectInputStream().readObject();
			this.clientKey = this.lastMessageManager.getTextMessage().get("uid");
		}
		return this.clientKey;
	}

	public ObjectInputStream getObjectInputStream() {
		if(this.objectInputStream == null) {
			try {
				this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.objectInputStream;
	}

	public ObjectOutputStream getObjectOutputStream() {
		if(this.objectOutputStream == null) {
			try {
				this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.objectOutputStream;
	}

}
