package com.kunyue.modbus.msg.resp;

import com.kunyue.modbus.msg.MsgType;
import com.kunyue.modbus.msg.RespMsgBody;
import com.kunyue.modbus.utils.BitOperator;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: silklee
 * @Date: 2020/8/2 12:24
 */
@Data
@Accessors(chain = true)
public class CommonReplyMsgBody implements RespMsgBody {

    // 1. 应答流水号 WORD terminal flowId
    private int replyFlowId;
    // 2. 应答id WORD 0x0102 ...
    private int replyMsgId;
    // 3. 结果  byte 0:成功/确认;1:失败;2:消息有误;3:不支持
    private byte result = SUCCESS;

    private CommonReplyMsgBody() {
    }

    public static CommonReplyMsgBody success(int replyFlowId, MsgType replyFor) {
        return of(SUCCESS, replyFlowId, replyFor);
    }

    public static CommonReplyMsgBody of(byte result, int replyFlowId, MsgType replyFor) {
        return new CommonReplyMsgBody()
                .setResult(result)
                .setReplyFlowId(replyFlowId)
                .setReplyMsgId(replyFor.getMsgId());
    }

    @Override
    public byte[] toBytes() {
        BitOperator bitOperator = new BitOperator();
        return bitOperator.concatAll(bitOperator.integerTo2Bytes(this.getReplyFlowId()),
                            bitOperator.integerTo2Bytes(this.getReplyMsgId()),
                            new byte[]{this.getResult()});
    }
}
