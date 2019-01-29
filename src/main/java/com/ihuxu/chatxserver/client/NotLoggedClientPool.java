package com.ihuxu.chatxserver.client;

import java.util.concurrent.LinkedBlockingQueue;
import com.ihuxu.chatxserver.conf.ClientConfig;

/**
 * ”未登录客户端“的线程池.
 * 
 * 单利模式（内部类方式 线程安全）
 * 
 * 功能：
 * 1. 提供“未登录客户端”线程的增加与删除操作
 * 2. 定时监控“客户端”的状态，如果状态非“未登录”状态，则将其进行相应处理
 * 	2.1 如果状态是已登录，则将其从当前“未登录客户端”的线程池移除；并将其增加到“已登录客户端”的线程池
 *  2.2 如果状态是断开连接，则将其从当前的“未登录客户端”的线程池移除；并销毁当前“客户端”线程
 * 
 * @author GenialX
 */
public class NotLoggedClientPool extends Thread {
    private static class LazyHolder {
        private final static NotLoggedClientPool instance = new NotLoggedClientPool();
    }
    
    /**
     * 未登录的客户端实例线程安全队列.
     */
    private static LinkedBlockingQueue<NotLoggedClient> clients = new LinkedBlockingQueue<NotLoggedClient>(ClientConfig.CLIENTS_QUEUE_CAPACITY);

    private NotLoggedClientPool() {}

    public static NotLoggedClientPool getInstance() {
        return LazyHolder.instance;
    }
    
    @SuppressWarnings("unused")
	private NotLoggedClient take() throws InterruptedException {
        return clients.take();
    }
    
    private NotLoggedClient peek() {
    	return clients.peek();
    }
    
    public boolean offer(NotLoggedClient client) throws InterruptedException {
        return clients.offer(client);
    }
    
    public void run() {
        NotLoggedClient client;
        while (true) {
            client = peek();
            if (client == null) {
            	try {
					NotLoggedClientPool.sleep(ClientConfig.CLIENTS_POOL_INTERVAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }
    }
}
