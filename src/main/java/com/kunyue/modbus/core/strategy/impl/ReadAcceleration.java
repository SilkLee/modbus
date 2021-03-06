package com.kunyue.modbus.core.strategy.impl;

import cn.hutool.core.util.HexUtil;
import com.google.common.primitives.Bytes;
import com.kunyue.modbus.core.strategy.Strategy;
import com.kunyue.modbus.utils.BitOperator;
import com.kunyue.modbus.vo.PackageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:21
 */
public class ReadAcceleration<T> implements Strategy<String> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String execute(PackageData packageData) {
        PackageData.MsgHeader msgHeader = packageData.getMsgHeader();
        logger.info(">>>>>[终端心跳],deviceaddr={}", msgHeader.getAddr());
        String str = null;
        try {
            List list = BitOperator.getFiledName(msgHeader);
            Bytes.asList(packageData.getMsgBodyBytes()).stream()
                    .forEach(var -> {
                        list.add(var);
                    });
            list.add(packageData.getCheckSum());
            byte[] bytes = Bytes.toArray(list);
            str = HexUtil.encodeHexStr(bytes);
        } catch (Exception e) {
            logger.error(">>>>>[终端心跳],deviceaddr={}", msgHeader.getAddr(), e.getMessage());
            e.printStackTrace();
        }
        return str;
    }
}
