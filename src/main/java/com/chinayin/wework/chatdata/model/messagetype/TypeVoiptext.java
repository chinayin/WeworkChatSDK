package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 音视频通话
 */
@Data
public class TypeVoiptext extends AbstractTypeFile {

    @JSONField(name = "callduration")
    private Integer callduration;

    @JSONField(name = "invitetype")
    private Integer invitetype;

}
