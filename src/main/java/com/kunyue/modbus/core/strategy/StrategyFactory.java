package com.kunyue.modbus.core.strategy;

import com.kunyue.modbus.constant.CmdTypeEnum;
import com.kunyue.modbus.core.strategy.impl.ReadAcceleration;
import com.kunyue.modbus.core.strategy.impl.ReadSensorInfo;
import com.kunyue.modbus.core.strategy.impl.SensorReadyJudge;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 10:42
 */
public class StrategyFactory {
    private static StrategyFactory strategyFactory = new StrategyFactory();
    private static Map<CmdTypeEnum, Strategy> map = new HashMap<>();

    /*
    * SensorReady(0x55, "传感器准备好"),
    ReadfromSensorInfo(0x01,"读取传感器基本信息"),
    SensorSleepy(0x0a, "传感器进入休眠状态"),
    ModifySensorAddr(0x08, "修改传感器地址"),
    ReadAcceleration(0x11, "读取加速度值"),
    AccWaveformSampling(0x14, "采集加速度波形"),
    ReadSpeedValue(0x21, "读取速度值"),
    SpeedWaveformSampling(0x24, "采集速度波形"),
    ReadDisplacement(0x31, "读取位移值"),
    DisplacementWaveformSampling(0x34, "采集位移波形"),
    TemperatureSampling(0x61, "打开红外指示并采集温度值"),
    RevolvingSpeedSamping(0x51, "读取转速值");
    * */

    static {
        map.put(CmdTypeEnum.ReadfromSensorInfo, new ReadSensorInfo());
        map.put(CmdTypeEnum.SensorReady, new SensorReadyJudge());
        map.put(CmdTypeEnum.ReadAcceleration, new ReadAcceleration());
    }

    public static StrategyFactory getInstance() {
        return strategyFactory;
    }

    public Strategy create(CmdTypeEnum cmdTypeEnum) {
        return map.get(cmdTypeEnum);
    }
}
