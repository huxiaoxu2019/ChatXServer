package com.ihuxu.chatxserver.conf;

import java.util.concurrent.TimeUnit;

public interface ClientConfig {
    // Client Login Pool
    public static final int OFFER_CLIENT_TIMEOUT = 20;
    public static final TimeUnit OFFER_CLIENT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    public static final int CLIENTS_QUEUE_CAPACITY = 10;
    public static final int CLIENTS_POOL_INTERVAL = 1000; // Unit Milliseconds
}
