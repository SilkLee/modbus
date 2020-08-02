package com.kunyue.modbus;

import com.kunyue.modbus.core.handler.ServerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:42
 */
@SpringBootApplication
public class ModbusApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ModbusApplication.class, args);
        ServerHandler.getServerHandler().sendToServer("");
    }

}
