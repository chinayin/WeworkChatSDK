package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 名片消息
 */
@Data
public class TypeCard {

    /**
     * 名片所有者所在的公司名称
     */
    @JSONField(name = "corpname")
    private String corpName;

    /**
     * 名片所有者的id，同一公司是userid，不同公司是external_userid
     */
    @JSONField(name = "userid")
    private String userId;

}
