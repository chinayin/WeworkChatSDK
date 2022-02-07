package com.chinayin.wework.chatdata.model.messagetype;

import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 位置消息
 */
@Data
public class TypeLocation {

    /**
     * 经度，单位double
     */
    private Double longitude;

    /**
     * 经纬度度，单位double
     */
    private Double latitude;

    /**
     * 地址信息
     */
    private String address;

    /**
     * 位置信息的title
     */
    private String title;

    /**
     * 缩放比例
     */
    private Integer zoom;

}
