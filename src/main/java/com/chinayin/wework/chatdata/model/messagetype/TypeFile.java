package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 文件消息
 */
@Data
public class TypeFile extends AbstractTypeFile {

    /**
     * 文件名称。String类型
     */
    private String filename;

    /**
     * 文件类型后缀。String类型
     */
    @JSONField(name = "fileext")
    private String fileExt;

}
