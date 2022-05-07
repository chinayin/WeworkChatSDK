package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 红包消息
 */
@Data
public class TypeRedPacket {

    /**
     * 红包消息类型。1 普通红包、2 拼手气群红包、3 激励群红包
     */
    private Integer type;

    /**
     * 红包祝福语
     */
    private String wish;

    /**
     * 红包总个数
     */
    @JSONField(name = "totalcnt")
    private Integer totalCnt;

    /**
     * 红包总金额，单位为分
     */
    @JSONField(name = "totalamount")
    private Integer totalAmount;

}
