package com.ihuxu.chatxserver.client;

/**
 * 客户端状态类.
 * 
 * 客户端状态机的描述元素。
 * 
 * @author GenialX
 */
public class ClientStatus {
	public int status;
	public ClientStatus parent;
	public ClientStatus[] next;
}
