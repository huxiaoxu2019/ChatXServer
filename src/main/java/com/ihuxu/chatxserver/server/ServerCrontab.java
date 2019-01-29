package com.ihuxu.chatxserver.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ihuxu.chatxserver.common.model.MessagePackage;

public class ServerCrontab {

	private static boolean clientSockedChecked = false;

	public ServerCrontab() {}

	public static void checkClientSocket() {
		if (ServerCrontab.isClientSocketChecked()) {
			System.out.println("has already checked.");
			return;
		} else {
			ServerCrontab.setClientSockedChecked(true);
		}
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("ServerCrontab -> the client thread count is " + ClientThreadManager.getClientThreadsCount());
				ClientThreadManager.cleanClientThreadsGarbage();
				try {
					MessagePackageManager.writeMessagePackages(MessagePackage.TYPE_CHAT_TEXT_MSG);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, com.ihuxu.chatxserver.conf.ServerConfig.CRONTAB_INTERVAL, com.ihuxu.chatxserver.conf.ServerConfig.CRONTAB_INTERVAL, TimeUnit.MILLISECONDS);
	}

	public static boolean isClientSocketChecked() {
		return clientSockedChecked;
	}

	public static void setClientSockedChecked(boolean clientSockedChecked) {
		ServerCrontab.clientSockedChecked = clientSockedChecked;
	}

}
