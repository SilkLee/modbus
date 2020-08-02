package com.kunyue.modbus.queue.listener;

import com.kunyue.modbus.queue.RequestMsgQueue;
import com.kunyue.modbus.queue.RequestMsgQueueListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 13:20
 */
@Slf4j
public abstract class AbstractRequestMsgQueueListener<T extends RequestMsgQueue> implements RequestMsgQueueListener {

}
