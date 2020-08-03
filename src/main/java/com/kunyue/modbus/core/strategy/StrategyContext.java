package com.kunyue.modbus.core.strategy;

import cn.hutool.core.convert.Convert;
import com.kunyue.modbus.code.MsgDecoder;
import com.kunyue.modbus.constant.CmdTypeEnum;
import com.kunyue.modbus.vo.PackageData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:42
 */
public class StrategyContext {

    private Strategy strategy;

    private MsgDecoder decoder = new MsgDecoder();

    public String getResult(String pack) {

        //byte[] bytes = Convert.hexToBytes(pack);
        //byte[] bytes = new byte[]{0x01, 0x40, 0x55, 0x08, 0x00, (byte) 0xc6, 0x02, (byte) 0xe1, (byte) 0xfa, 0x1d, (byte) 0x85, 0x64, 0x00, 0x47};
        byte[] bytes = new byte[]{0x01, (byte) 0x80, 0x11, 0x00, 0x00, (byte) 0x92};

        // 字节数据转换为针对于zd710消息结构的实体类
        PackageData packageData = this.decoder.bytes2PackageData(bytes);

        Integer type = packageData.getMsgHeader().getCmd();
        Strategy strategy = StrategyFactory.getInstance().create(CmdTypeEnum.from(type));
        if (strategy == null) {
            throw new RuntimeException("策略生成错误");
        }
        return strategy.execute(packageData).toString();
    }
}
