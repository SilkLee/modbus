package com.kunyue.modbus;

import com.kunyue.modbus.core.handler.ServerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:42
 */
@SpringBootApplication
@ComponentScan("com.kunyue.modbus.code")
public class ModbusApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ModbusApplication.class, args);
        ServerHandler.getServerHandler().sendToServer("");
    }

}
