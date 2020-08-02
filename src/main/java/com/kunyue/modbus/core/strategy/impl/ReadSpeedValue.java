package com.kunyue.modbus.core.strategy.impl;

import com.kunyue.modbus.core.strategy.Strategy;
import com.kunyue.modbus.vo.PackageData;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:22
 */
@Service
public class ReadSpeedValue<T> implements Strategy<String> {
    @Override
    public String execute(PackageData packageData, String pack) {
        return null;
    }
}
