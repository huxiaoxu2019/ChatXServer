package com.ihuxu.chatxserver.util.server;

import java.util.HashMap;

public class ClientThreadManager {

	public static HashMap<String, ClientThread> clientServerThreadHashMap = new HashMap<String, ClientThread>();
	
	public static ClientThread getClientThread(String key) throws Exception {
		if(ClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
			return ClientThreadManager.clientServerThreadHashMap.get(key);
		} else {
			throw new Exception("There is not the ClientThread in the clientServerThreadHashMap variable.");
		}
	}
	
	public static ClientThread setClientThread(String key, ClientThread clientThread) {
		return ClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
	}
	
	public static boolean addClientThread(String key, ClientThread clientThread) {
		if(!ClientThreadManager.clientServerThreadHashMap.containsKey(key)) {
			ClientThreadManager.clientServerThreadHashMap.put(key, clientThread);
			return true;
		}
		return false;
	}
	
}
