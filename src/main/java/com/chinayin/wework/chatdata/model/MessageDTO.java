package com.chinayin.wework.chatdata.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.chinayin.wework.chatdata.model.messagetype.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 企业微信接口获取的实际解密后消息
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    /**
     * 消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
     */
    @JSONField(ordinal = 1, name = "action")
    private String action;

    /**
     * 消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
     */
    @JSONField(ordinal = 2, name = "msgid")
    private String msgId;

    /**
     * 消息类型。String类型
     */
    @JSONField(ordinal = 3, name = "msgtype")
    private String msgType;

    /**
     * 消息发送时间戳，utc时间，ms单位。
     */
    @JSONField(ordinal = 4, name = "msgtime")
    private Long msgTime;

    /**
     * 消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
     */
    @JSONField(ordinal = 20, name = "from")
    private String from;

    /**
     * 消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
     */
    @JSONField(ordinal = 20, name = "tolist")
    private List<String> toList;

    /**
     * 群聊消息的群id。如果是单聊则为空。String类型
     */
    @JSONField(ordinal = 20, name = "roomid")
    private String roomId;


    /**
     * ============ 消息类型 ============
     * @link https://developer.work.weixin.qq.com/document/path/91774
     */

    /**
     * 文本
     */
    @JSONField(ordinal = 10, name = "text")
    private TypeText text;

    /**
     * 图片
     */
    @JSONField(ordinal = 10, name = "image")
    private TypeImage image;

    /**
     * 撤回消息
     */
    @JSONField(ordinal = 10, name = "revoke")
    private TypeRevoke revoke;

    /**
     * 同意会话聊天内容
     */
    @JSONField(ordinal = 10, name = "agree")
    private TypeAgree agree;

    @JSONField(ordinal = 10, name = "disagree")
    private TypeAgree disagree;

    /**
     * 语音
     */
    @JSONField(ordinal = 10, name = "voice")
    private TypeVoice voice;

    /**
     * 视频
     */
    @JSONField(ordinal = 10, name = "video")
    private TypeVideo video;

    /**
     * 名片
     */
    @JSONField(ordinal = 10, name = "card")
    private TypeCard card;

    /**
     * 位置
     */
    @JSONField(ordinal = 10, name = "location")
    private TypeLocation location;

    /**
     * 表情
     */
    @JSONField(ordinal = 10, name = "emotion")
    private TypeEmotion emotion;

    /**
     * 文件
     */
    @JSONField(ordinal = 10, name = "file")
    private TypeFile file;

    /**
     * 链接
     */
    @JSONField(ordinal = 10, name = "link")
    private TypeLink link;

    /**
     * 小程序
     */
    @JSONField(ordinal = 10, name = "weapp")
    private TypeWeapp weapp;

    /**
     * 会话记录
     */
    @JSONField(ordinal = 10, name = "chatrecord")
    private TypeChatRecord chatRecord;

    /**
     * 待办
     */
    @JSONField(ordinal = 10, name = "todo")
    private TypeTodo todo;

    /**
     * 投票
     */
    @JSONField(ordinal = 10, name = "vote")
    private TypeVote vote;

    /**
     * 填表
     */
    @JSONField(ordinal = 10, name = "collect")
    private TypeCollect collect;

    /**
     * 红包
     */
    @JSONField(ordinal = 10, name = "redpacket")
    private TypeRedPacket redPacket;

    /**
     * 会议邀请
     */
    @JSONField(ordinal = 10, name = "meeting")
    private TypeMeeting meeting;

    /**
     * 切换企业日志
     * todo
     */

    /**
     * 在线文档
     */
    @JSONField(ordinal = 10, name = "doc")
    private TypeDoc doc;

    /**
     * markdown
     */
    @JSONField(ordinal = 10, name = "info")
    private TypeMarkdown markdown;

    /**
     * 图文
     */
    @JSONField(ordinal = 10, name = "info")
    private TypeNews news;

    /**
     * 日程
     */
    @JSONField(ordinal = 10, name = "calendar")
    private TypeCalendar calendar;

    /**
     * 混合消息
     */
    @JSONField(ordinal = 10, name = "mixed")
    private TypeMixed mixed;

    /**
     * 音频存档
     * todo
     */

    /**
     * 音频共享文档
     * todo
     */

    /**
     * 互通红包
     */
    @JSONField(ordinal = 10, name = "redpacket")
    private TypeRedPacket externalRedPacket;

    /**
     * 视频号
     */
    @JSONField(ordinal = 10, name = "sphfeed")
    private TypeSphFeed sphFeed;

}
