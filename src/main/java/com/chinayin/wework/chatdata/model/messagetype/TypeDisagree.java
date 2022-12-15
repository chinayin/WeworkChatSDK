package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 同意会话聊天内容消息(同意和不同意共用)
 */
@Data
public class TypeDisagree {

    /**
     * 同意/不同意协议者的userid，外部企业默认为external_userid
     */
    @JSONField(name = "userid")
    private String userId;

    /**
     * 同意/不同意协议的时间，utc时间，ms单位。
     */
    @JSONField(name = "disagree_time")
    private Long agreeTime;

}
