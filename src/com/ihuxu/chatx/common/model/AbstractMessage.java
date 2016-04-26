package com.ihuxu.chatx.common.model;

public abstract class AbstractMessage implements Message{
	
	private int type = Message.TYPE_UNKNOWN;
	
	public AbstractMessage(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

}
