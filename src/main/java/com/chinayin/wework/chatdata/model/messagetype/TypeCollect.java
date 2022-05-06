package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 填表消息
 */
@Data
public class TypeCollect {

    /**
     * 填表消息所在的群名称
     */
    @JSONField(name = "room_name")
    private String roomName;

    /**
     * 创建者在群中的名字
     */
    private String creator;

    /**
     * 创建的时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /**
     * 表名
     */
    private String title;

    /**
     * 表内容
     */
    private List<TypeCollectItem> details;

}

@Data
class TypeCollectItem {

    /**
     * 表项id
     */
    private long id;
    /**
     * 表项名称
     */
    private String ques;

    /**
     * 表项类型 有Text(文本),Number(数字),Date(日期),Time(时间)
     */
    private String type;

}
