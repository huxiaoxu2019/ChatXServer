package com.ihuxu.chatxserver.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Client {
	// 客户端状态机
	public final static int STATUS_NOT_LOGGED = 0x01;
	public final static int STATUS_LOGGED = 0x02;
	public final static int STATUS_DISCONNECTED = 0x03;
	
	public InputStream getInput() throws IOException;
	public OutputStream getOutput() throws IOException;
	public void setStatus(int status);
	public int getStatus();
}
