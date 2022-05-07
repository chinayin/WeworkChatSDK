package com.chinayin.wework.chatdata.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 企业微信接口获取的传输内层对象
 */
@Data
public class ChatDataDetailDTO {

    /**
     * 消息的seq值，标识消息的序号。再次拉取需要带上上次回包中最大的seq。Uint64类型，范围0-pow(2,64)-1
     */
    @JSONField(name = "seq")
    private long seq;

    /**
     * 消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型。msgid以_external结尾的消息，表明该消息是一条外部消息。
     */
    @JSONField(name = "msgid")
    private String msgId;

    /**
     * 加密此条消息使用的公钥版本号。Uint32类型
     */
    @JSONField(name = "publickey_ver")
    private Integer publicKeyVer;

    /**
     * 使用publickey_ver指定版本的公钥进行非对称加密后base64加密的内容，需要业务方先base64 decode处理后，再使用指定版本的私钥进行解密，得出内容。String类型
     */
    @JSONField(name = "encrypt_random_key")
    private String encryptRandomKey;

    /**
     * 消息密文。需要业务方使用将encrypt_random_key解密得到的内容，与encrypt_chat_msg，传入sdk接口DecryptData,得到消息明文。String类型
     */
    @JSONField(name = "encrypt_chat_msg")
    private String encryptChatMsg;

}
