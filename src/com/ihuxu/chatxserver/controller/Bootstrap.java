package com.ihuxu.chatxserver.controller;

import com.ihuxu.chatxserver.common.model.MessagePackage;
import com.ihuxu.chatxserver.common.model.TextMessage;
import com.ihuxu.chatxserver.util.server.Server;

public class Bootstrap {
	public static void main(String []args) {
		Server server = new Server();
		server.start();
	}
}
