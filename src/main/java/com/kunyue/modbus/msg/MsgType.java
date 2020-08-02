package com.kunyue.modbus.msg;

import java.util.Optional;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:32
 */
public interface MsgType {
    int getMsgId();

    String getDesc();

    default Optional<MsgType> parseFromInt(int msgId) {
        throw new UnsupportedOperationException("this method should be override in subclass");
    }

    String toString();
}
