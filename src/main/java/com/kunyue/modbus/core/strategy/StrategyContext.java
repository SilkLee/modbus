package com.kunyue.modbus.core.strategy;

import com.kunyue.modbus.constant.CmdTypeEnum;
import com.kunyue.modbus.vo.PackageData;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:42
 */
@Service
public class StrategyContext {
    private Strategy strategy;

    public String getResult(Integer type, PackageData packageData, String pack) {
        Strategy strategy = StrategyFactory.getInstance().create(CmdTypeEnum.from(type));
        if (strategy == null) {
            throw new RuntimeException("策略生成错误");
        }
        return strategy.execute(packageData, pack).toString();
    }
}
