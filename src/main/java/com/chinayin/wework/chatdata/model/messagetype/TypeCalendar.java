package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 日程消息
 */
@Data
public class TypeCalendar {

    /**
     * 日程主题
     */
    private String title;

    /**
     * 日程组织者
     */
    @JSONField(name = "creatorname")
    private String creatorName;

    /**
     * 日程参与人
     */
    @JSONField(name = "attendeename")
    private String[] attendeeName;

    /**
     * 日程开始时间。Utc时间，单位秒
     */
    @JSONField(name = "starttime")
    private Long startTime;

    /**
     * 日程结束时间。Utc时间，单位秒
     */
    @JSONField(name = "endtime")
    private Long endTime;

    /**
     * 日程地点
     */
    private String place;

    /**
     * 日程备注
     */
    private String remarks;

}
