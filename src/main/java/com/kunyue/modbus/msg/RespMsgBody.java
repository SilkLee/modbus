package com.kunyue.modbus.msg;

import com.kunyue.modbus.constant.TPMSConsts;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public interface RespMsgBody {
    byte SUCCESS = 0;
    byte FAILURE = 1;

    byte[] toBytes();

}
