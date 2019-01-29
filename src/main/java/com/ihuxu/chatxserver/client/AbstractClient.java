package com.ihuxu.chatxserver.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class AbstractClient extends Thread implements Client {
	private Socket socket;
	@SuppressWarnings("unused")
	private InputStream input;
	@SuppressWarnings("unused")
	private OutputStream output;
	
	public AbstractClient(Socket socket) {
		this.socket = socket;
	}
	
	public InputStream getInput() throws IOException {
		return socket.getInputStream();
	}
	
	public OutputStream getOutput() throws IOException {
		return socket.getOutputStream();
	}
	
	abstract public void run();
}
