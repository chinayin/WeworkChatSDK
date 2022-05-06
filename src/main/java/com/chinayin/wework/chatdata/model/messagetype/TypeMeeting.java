package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 会议邀请消息
 */
@Data
public class TypeMeeting {

    /**
     * 会议主题
     */
    private String topic;

    /**
     * 会议开始时间 Utc时间
     */
    @JSONField(name = "starttime")
    private Long startTime;

    /**
     * 会议结束时间 Utc时间
     */
    @JSONField(name = "endtime")
    private Long endTime;

    /**
     * 会议地址
     */
    private String address;

    /**
     * 会议备注
     */
    private String remarks;

    /**
     * 会议消息类型。101发起会议邀请消息、102处理会议邀请消息
     */
    @JSONField(name = "meetingtype")
    private Integer meetingType;

    /**
     * 会议id。方便将发起、处理消息进行对照
     */
    @JSONField(name = "meetingid")
    private Long meetingId;

    /**
     * 会议邀请处理状态。1参加会议、2拒绝会议、3待定、4未被邀请、5会议已取消、6会议已过期、7不在房间内。Uint32类型。只有meetingtype为102的时候此字段才有内容
     */
    @JSONField(name = "status")
    private Integer status;

}
