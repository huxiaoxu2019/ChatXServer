package com.ihuxu.chatxserver.test;

public class ClientStatus {

	public static void main(String[] args) {
		long a = calculateClientStatus(Long.parseLong(args[0]), Long.parseLong(args[1]));
		System.out.println(a);
	}
	
	public static long calculateClientStatus(long depth, long value) {
		System.out.println(depth);
		System.out.println(value);
		if ((depth & (1 << 7)) > 0) return -1;
		long result = (depth << 24) | (value & (Long.MAX_VALUE >> 7));
		return result;
	}
}
