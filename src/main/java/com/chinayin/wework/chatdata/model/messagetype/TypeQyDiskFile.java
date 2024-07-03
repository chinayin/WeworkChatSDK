package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 微盘文件
 */
@Data
public class TypeQyDiskFile {

    /**
     * 文件名称
     */
    @JSONField(name = "filename")
    private String filename;

}
