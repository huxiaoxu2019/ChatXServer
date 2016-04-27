package com.ihuxu.chatxserver.controller;

import com.ihuxu.chatxserver.common.model.MessageManager;
import com.ihuxu.chatxserver.common.model.TextMessage;
import com.ihuxu.chatxserver.util.server.Server;

public class Bootstrap {
	public static void main(String []args) {
		/**
		
		TextMessage textMessage = new TextMessage();
		textMessage.set("name", "genialx");
		textMessage.set("uid", "23392282");
		MessageManager messageManager = new MessageManager(MessageManager.TYPE_CHAT_TEXT_MSG);
		messageManager.setTextMessage(textMessage);
		
		if(messageManager.hasTextMessage()) {
			System.out.println(messageManager.getTextMessage());
		}
		
		**/
		Server server = new Server();
		server.start();
		
	}
}
