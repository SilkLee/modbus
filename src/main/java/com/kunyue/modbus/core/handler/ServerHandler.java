package com.kunyue.modbus.core.handler;

import com.kunyue.modbus.core.strategy.StrategyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public class ServerHandler {
    private volatile static ServerHandler serverHandler;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ServerHandler() {
    }

    public static ServerHandler getServerHandler() {
        if (serverHandler == null) {
            synchronized (ServerHandler.class) {
                if (serverHandler == null) {
                    serverHandler = new ServerHandler();
                }
            }
        }
        return serverHandler;
    }

    public String sendToServer(String pack) throws Exception {
        String result = null;
        try {
            StrategyContext strategyContext = new StrategyContext();
            result = strategyContext.getResult(pack);
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
}