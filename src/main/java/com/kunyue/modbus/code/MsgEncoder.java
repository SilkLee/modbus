package com.kunyue.modbus.code;

import com.google.common.primitives.Bytes;
import com.kunyue.modbus.utils.BitOperator;
import com.kunyue.modbus.vo.PackageData;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public class MsgEncoder {
    private BitOperator bitOperator;

    public MsgEncoder() {
        this.bitOperator = new BitOperator();
    }

    public byte[] encode4ParamSetting(PackageData packageData, byte[] bs) throws Exception {
        // 消息头
        List list = BitOperator.getFiledName(packageData.getMsgHeader());
        byte[] msgHeader = Bytes.toArray(list);
        // 连接消息头和消息体
        byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, bs);
        // 校验码
        int checkSum = this.bitOperator.getCheckSum(headerAndBody, 0, headerAndBody.length - 1);
        // 连接并且转义
        return this.doEncode(headerAndBody, checkSum);
    }

    private byte[] doEncode(byte[] headerAndBody, int checkSum) throws Exception {
        byte[] noEscapedBytes = this.bitOperator.concatAll(Arrays.asList(//
                headerAndBody, // 消息头+ 消息体
                bitOperator.integerTo1Bytes(checkSum) // 校验码
        ));
        // 转义
        return noEscapedBytes;
    }
}
