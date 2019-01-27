package com.ihuxu.chatxserver.client;

import java.util.concurrent.LinkedBlockingQueue;
import com.ihuxu.chatxserver.conf.ClientConfig;

/**
 * 未登录的客户端线程池.
 * 
 * 单利模式（内部类方式 线程安全）
 * 
 * @author GenialX
 *
 */
public class ClientLoginPool extends Thread {
    
    private static class LazyHolder {
        private final static ClientLoginPool instance = new ClientLoginPool();
    }
    
    /**
     * 未登录的客户端实例线程安全队列.
     */
    private static LinkedBlockingQueue<Client> clients = new LinkedBlockingQueue<Client>(ClientConfig.CLIENTS_QUEUE_CAPACITY);

    private ClientLoginPool() {}

    public static ClientLoginPool getInstance() {
        return LazyHolder.instance;
    }
    
    public Client take() throws InterruptedException {
        return clients.take();
    }
    
    public boolean offer(Client client) throws InterruptedException {
        return clients.offer(client);
    }
    
    public void run() {
        Client currentClient;
        while (true) {
            try {
                currentClient = take();
            } catch (InterruptedException e) {
                System.err.println("take client from queue failed.");
                e.printStackTrace();
                continue;
            }
        }
    }
}
