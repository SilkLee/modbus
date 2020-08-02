package com.kunyue.modbus.constant;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public class TPMSConsts {

    public static final String string_encoding = "UTF-8";

    public static final Charset string_charset = Charset.forName(string_encoding);

    // 标识位
    public static final int pkg_delimiter = 0x80;

    // 终端心跳
    public static final int terminal_heart_beat = 0x55;

}

