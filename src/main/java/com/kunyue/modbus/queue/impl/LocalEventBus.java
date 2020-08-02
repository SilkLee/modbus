package com.kunyue.modbus.queue.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.kunyue.modbus.queue.RequestMsgQueue;

import java.util.concurrent.Executor;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 13:22
 */
public class LocalEventBus extends AsyncEventBus implements RequestMsgQueue {

    public LocalEventBus(String identifier, Executor executor) {
        super(identifier, executor);
    }

    public LocalEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(executor, subscriberExceptionHandler);
    }

    public LocalEventBus(Executor executor) {
        super(executor);
    }

}
