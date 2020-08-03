package com.kunyue.modbus.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/3 9:47
 */
@Getter
@Setter
@Data
public class PackageData {

    private MsgHeader msgHeader;

    // DATA_LEN[5～18] 数据内容
    private byte[] msgBodyBytes;

    // CRC[19] CRC校验
    private int checkSum;

    @Getter
    @Setter
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
