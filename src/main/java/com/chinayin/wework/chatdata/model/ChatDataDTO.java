package com.chinayin.wework.chatdata.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 企业微信接口获取的传输对象
 */
@Data
public class ChatDataDTO {

    /**
     * 0表示成功，错误返回非0错误码，需要参看errmsg。Uint32类型
     */
    @JSONField(name = "errcode")
    private Integer errCode;

    /**
     * 返回信息，如非空为错误原因。String类型
     */
    @JSONField(name = "errmsg")
    private String errMsg;

    /**
     * 聊天记录数据内容。数组类型。包括seq、msgid等内容
     */
    @JSONField(name = "chatdata")
    protected List<ChatDataDetailDTO> chatData;

}
