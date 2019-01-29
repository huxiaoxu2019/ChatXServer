package com.ihuxu.chatxserver.client;

import java.net.Socket;

/**
 * 客户端线程.
 * 
 * 功能：
 *  1. 与”客户端“进行登录校验
 *  2. 更新当前“客户端”的状态
 *  
 * @author GenialX
 */
public class NotLoggedClient extends AbstractClient {
	private int status = this.STATUS_NOT_LOGGED;

	public NotLoggedClient(Socket socket) {
		super(socket);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}
}
