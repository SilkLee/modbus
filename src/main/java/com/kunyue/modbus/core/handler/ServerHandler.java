package com.kunyue.modbus.core.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.HexUtil;
import com.kunyue.modbus.code.MsgDecoder;
import com.kunyue.modbus.core.strategy.StrategyContext;
import com.kunyue.modbus.core.strategy.impl.SensorReadyJudge;
import com.kunyue.modbus.vo.PackageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public class ServerHandler {
    private volatile static ServerHandler serverHandler;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MsgDecoder decoder;

    @Autowired
    private SensorReadyJudge sensorReadyJudge;

    private ServerHandler() {
        decoder = new MsgDecoder();
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
            byte[] bs = new byte[]{0x01, 0x40, 0x55, 0x08, 0x00, (byte) 0xc6, 0x02, (byte) 0xe1, (byte) 0xfa, 0x1d, (byte) 0x85, 0x64, 0x00, 0x47};
            final String strPack = HexUtil.encodeHexStr(bs);
            byte[] bytes = Convert.hexToBytes(strPack);
            // 字节数据转换为针对于zd710消息结构的实体类
            PackageData packageData = this.decoder.bytes2PackageData(bytes);
            StrategyContext strategyContext = new StrategyContext();
            result = strategyContext.getResult(packageData.getMsgHeader().getCmd(), packageData, pack);
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
}