package com.ihuxu.chatxserver.util.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ihuxu.chatxserver.common.model.MessagePackage;
import com.ihuxu.chatxserver.common.model.TextMessage;

public class ClientThread extends Thread{
	
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private MessagePackage loginMessagePackage;
	private boolean listened = true;
	private String clientKey;
	
	public ClientThread(Socket socket) throws Exception {
		super();
		this.socket = socket;
		this.readAndSetLoginMessagePackage();
		this.clientKey = this.getClientKey();
		this.writeLoginMessagePackage();
	}
	
	public MessagePackage readAndSetLoginMessagePackage() throws Exception {
		if(this.loginMessagePackage == null) {
			try {
				this.loginMessagePackage = (MessagePackage) this.getObjectInputStream().readObject();
				if(this.loginMessagePackage.getType() != MessagePackage.TYPE_LOGIN_MSG) {
					throw new Exception("the first message package is not login msg type package.");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.loginMessagePackage;
	}
	
	public void writeLoginMessagePackage() {
		try {
			MessagePackage mP = new MessagePackage(MessagePackage.TYPE_LOGIN_SUC_MSG);
			this.getObjectOutputStream().writeObject(mP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeMessagePackage(MessagePackage messagePackage) {
		try {
			this.getObjectOutputStream().writeObject(messagePackage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("client server thread running...");
		while(this.listened) {
			try {
				System.out.println("client server thread listening...");
				MessagePackage messagePackage = (MessagePackage) this.getObjectInputStream().readObject();
				System.out.println("recieved one object from client -> " + this.getClientKey());
				switch(messagePackage.getType()) {
				case MessagePackage.TYPE_CHAT_TEXT_MSG:
					System.out.println("the message package type is type_chat_text_msg. to:" + messagePackage.getTextMessage().getTo());
					MessagePackageManager.pushMessgagePackageToQueue(MessagePackage.TYPE_CHAT_TEXT_MSG, messagePackage);
					break;
				default:
					throw new Exception("unknow MessagePackage type.");
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
			this.clientKey = Long.toString(this.readAndSetLoginMessagePackage().getTextMessage().getFrom());
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
	
	public void close() {
		try {
			if(this.objectInputStream != null) {
					this.objectInputStream.close();
			}
			if(this.objectOutputStream != null) {
				this.objectOutputStream.close();
			}
			if(this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
