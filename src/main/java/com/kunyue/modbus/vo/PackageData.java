package com.kunyue.modbus.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/3 9:47
 */
@Data
public class PackageData {

    private MsgHeader msgHeader;

    // DATA_LEN[5～18] 数据内容
    private byte[] msgBodyBytes;

    // CRC[19] CRC校验
    private int checkSum;

    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public byte[] getMsgBodyBytes() {
        return msgBodyBytes;
    }

    public void setMsgBodyBytes(byte[] msgBodyBytes) {
        this.msgBodyBytes = msgBodyBytes;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    @Data
    public static class MsgHeader {
        // ADDR[0] 传感器地址
        private int addr;

        // FLAG[1] 0x80代表下发命令（数据帧下发，主设备->传感器），0x40代表（数据帧上传，传感器->主设备）
        private int flag;

        // CMD[2] 传感器cmd
        private int cmd;

        // LEN_LOW[3] LEN_HI[4] 数据长度，小端法
        private int len_low;
        private int len_hi;
    }

}
