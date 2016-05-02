package com.ihuxu.chatxserver.common.model;

import java.io.Serializable;

public class MessageManager implements Manager, Serializable{

	private static final long serialVersionUID = 5090328872687319042L;
	
	private int type = MessageManager.TYPE_UNKNOWN;
	
	private TextMessage textMessage;
	
	public MessageManager(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setTextMessage(TextMessage textMessage) {
		this.textMessage = textMessage;
	}
	
	public TextMessage getTextMessage() {
		return this.textMessage;
	}
	
	public boolean hasTextMessage() {
		if(this.textMessage == null) {
			return false;
		}
		return true;
	}
	
}
