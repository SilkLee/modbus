package com.kunyue.modbus.code;

import cn.hutool.core.convert.Convert;
import com.google.common.primitives.Bytes;
import com.kunyue.modbus.utils.BitOperator;
import com.kunyue.modbus.vo.PackageData;
import com.kunyue.modbus.vo.PackageData.MsgHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public class MsgDecoder {
    private static final Logger log = LoggerFactory.getLogger(MsgDecoder.class);

    private final BitOperator bitOperator;

    public MsgDecoder() {
        this.bitOperator = new BitOperator();
    }

    // 字节数组到消息体实体类
    public PackageData bytes2PackageData(byte[] data) {
        PackageData packageData = new PackageData();
        MsgHeader msgHeader = this.parseMsgHeaderFromBytes(data);

        // 校验和比较
        int checkSumCode = data[data.length - 1];
        packageData.setCheckSum(checkSumCode);

        int calculatedCheckSum = this.bitOperator.getCheckSum(data, 0, data.length - 2);

        if (checkSumCode != calculatedCheckSum) {
            log.warn("检验码不一致,devaddr:{},code:{},calculated:{}", msgHeader.getAddr(), checkSumCode, calculatedCheckSum);
            packageData = null;
        } else {
            // 5bytes的消息头
            packageData.setMsgHeader(msgHeader);

            // 消息体
            byte hiByte = Convert.intToByte(msgHeader.getLen_hi());
            byte lowByte = Convert.intToByte(msgHeader.getLen_low());
            List list = new ArrayList();
            list.add(hiByte);
            list.add(lowByte);

            int bodyLength = Convert.bytesToShort(Bytes.toArray(list));

            byte[] bytes = new byte[bodyLength];

            int index = Bytes.indexOf(data, (byte) 0xff);
            if (index == -1) {
                index = 0;
            } else {
                index = 1;
            }

            System.arraycopy(data, index + 5, bytes, 0, bytes.length);
            packageData.setMsgBodyBytes(bytes);
        }

        return packageData;
    }


    // 将字节数组解析成消息头
    public MsgHeader parseMsgHeaderFromBytes(byte[] data) {
        MsgHeader msgHeader = new MsgHeader();

        // 1、ADDR[0] 传感器地址
        int addr = Bytes.hashCode(data[0]);
        msgHeader.setAddr(addr);

        // 2、FLAG[1]
        int flag = Bytes.hashCode(data[1]);
        msgHeader.setFlag(flag);

        // 3、CMD[2] 传感器cmd
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 64 || data[i] == -128) {
                msgHeader.setCmd(Bytes.hashCode(data[i + 1]));
            }
        }

        // 4、LEN_LOW[3] LEN_HI[4] 数据长度，小端法
        int index = Bytes.indexOf(data, (byte) 0xff);

        if (index == -1) {
            index = 0;
        } else {
            index = 1;
        }

        int lenLow = Bytes.hashCode(data[index + 3]);
        int lenHi = Bytes.hashCode(data[index + 4]);
        msgHeader.setLen_low(lenLow);
        msgHeader.setLen_hi(lenHi);

        return msgHeader;
    }

    // 将字节数组解析成字符串
    public String parseStringFromBytes(byte[] data, int startIndex, int length) {
        try {
            byte[] bytes = new byte[length];
            System.arraycopy(data, startIndex, bytes, 0, length);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("解析字符串出错：{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 将字节数组反转并解析成整型
    private int parseIntFromBytes(byte[] data, int startIndex, int length) {
        try {
            // 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
            final int len = length > 4 ? 4 : length;
            byte low = data[startIndex];
            byte high = data[startIndex + 1];

            List<Byte> list = new ArrayList<>();
            list.add(high);
            list.add(low);
            byte[] bytes = Bytes.toArray(list);
            return bitOperator.byteToInteger(bytes);
        } catch (Exception e) {
            log.error("解析整数出错:{}", e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

}
