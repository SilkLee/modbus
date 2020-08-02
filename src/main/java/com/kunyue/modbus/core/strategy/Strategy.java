package com.kunyue.modbus.core.strategy;

import com.kunyue.modbus.vo.PackageData;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:30
 */
public interface Strategy<T> {
    T execute(PackageData packageData, T pack);
}
