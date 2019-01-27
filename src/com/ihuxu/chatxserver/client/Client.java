package com.ihuxu.chatxserver.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端线程.
 * 
 * @author GenialX
 */
public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	public Client(Socket socket) {
	    this.socket = socket;
	}

    public void run() {
        
    }
}
