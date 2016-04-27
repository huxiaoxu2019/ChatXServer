package com.ihuxu.chatxserver.util.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerCrontab {
	
	private static boolean clientSockedChecked = false;

	public ServerCrontab() {}
	
	public static void checkClientSocket() {
		if(ServerCrontab.isClientSockedChecked()) {
			System.out.println("has already checked.");
			return;
		} else {
			ServerCrontab.setClientSockedChecked(true);
		}
		Runnable runnable = new Runnable() {  
            public void run() {  
                System.out.println("ServerCrontab -> checkClientSocket...");  
                System.out.println("the client thread count is " + ClientThreadManager.clientServerThreadHashMap.size());
            }  
        };  
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.SECONDS);  
	}

	public static boolean isClientSockedChecked() {
		return clientSockedChecked;
	}

	public static void setClientSockedChecked(boolean clientSockedChecked) {
		ServerCrontab.clientSockedChecked = clientSockedChecked;
	}
	
}
