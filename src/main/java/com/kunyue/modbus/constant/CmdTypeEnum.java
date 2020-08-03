package com.kunyue.modbus.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 9:47
 */
public enum CmdTypeEnum {

    SensorReady(0x55, "传感器准备好"),
    ReadfromSensorInfo(0x01, "读取传感器基本信息"),
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

    CmdTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    private String description;

    public static CmdTypeEnum from(int value) {
        return Arrays.stream(values()).filter(element -> element.getValue() == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("error value for CmdTypeEnum"));
    }


}
