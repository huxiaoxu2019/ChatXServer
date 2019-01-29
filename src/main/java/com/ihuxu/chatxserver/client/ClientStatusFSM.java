package com.ihuxu.chatxserver.client;

/**
 * 客户端状态机（单例模式）.
 * 
 * 功能：
 *  1. 根据 ClientStatusFSM.CLIENT_STATUS_FSM 构造树型数据结构。
 * 
 * @author GenialX
 */
public class ClientStatusFSM {
    // Client FSM
    final public static String CLIENT_STATUS_FSM = "[{\"status\":0, \"desc\":\"未登录\",\"parent\":-1, \"next\":[{\"status\":72057594037927936, \"desc\":\"已登录\", \"parent\":0, \"next\":[{\"status\": 144115188075855872, \"desc\":\"登录失败\",\"parent\":0, \"next\":[]},{\"status\":144115188075855873, \"desc\":\"断开\",\"parent\":0, \"next\":[]}]},{\"status\": 72057594037927937, \"desc\":\"登录失败\",\"parent\":0, \"next\":[]},{\"status\": 72057594037927938, \"desc\":\"断开\",\"parent\":0, \"next\":[]}]}]";
    // For Level 0
	final public static long CLIENT_STATUS_LEVEL_0_NOT_LOGGED = 0;
	// For Level 1
	final public static long CLIENT_STATUS_LEVEL_1_LOGGED = 16777216;
	final public static long CLIENT_STATUS_LEVEL_1_LOGIN_FAILED = 16777217;
	final public static long CLIENT_STATUS_LEVEL_1_DISCONNECTED = 16777218;
	// For Level 2
	final public static long CLIENT_STATUS_LEVEL_2_LOGGED_OUT = 33554432;
	final public static long CLIENT_STATUS_LEVEL_2_DISCONNECTED = 33554433;
	
	private ClientStatusFSM() {
		this.build();
	}
	
	/*
	 * 构建状态机 —— 树型数据结构.
	 */
	private void build() {
		//LocalTime currentTime = new LocalTime();
		//gson gson = new Gson();
		//Gson gson = new Gson();
	}
	
	public static ClientStatusFSM getInstance() {
		return LazyHolder.instance;
	}

	private static class LazyHolder {
		private final static ClientStatusFSM instance = new ClientStatusFSM();
	}
}