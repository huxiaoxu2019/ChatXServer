package com.ihuxu.chatx.controller;

import com.ihuxu.chatx.common.model.MessageManager;
import com.ihuxu.chatx.common.model.TextMessage;

public class Bootstrap {
	public static void main(String []args) {
		System.out.println("chatx server is starting...");
		
		TextMessage textMessage = new TextMessage();
		textMessage.set("name", "genialx");
		textMessage.set("uid", "23392282");
		MessageManager messageManager = new MessageManager(MessageManager.TYPE_CHAT_TEXT_MSG);
		messageManager.setTextMessage(textMessage);
		
		if(messageManager.hasTextMessage()) {
			System.out.println(messageManager.getTextMessage());
		}
		
	}
}
