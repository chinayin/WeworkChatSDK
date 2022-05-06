package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 基础文件
 */
@Data
abstract public class AbstractTypeFile {

    /**
     * 媒体资源的id信息。String类型
     */
    @JSONField(name = "sdkfileid")
    private String sdkFileId;

    /**
     * 资源的md5值，供进行校验。String类型
     */
    private String md5sum;

    /**
     * 资源的文件大小。Uint32类型
     */
    @JSONField(name = "filesize")
    private Integer fileSize;
}
