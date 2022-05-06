package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 撤回消息
 */
@Data
public class TypeRevoke {

    /**
     * 标识撤回的原消息的msgid
     */
    @JSONField(name = "pre_msgid")
    private String preMsgId;

}
