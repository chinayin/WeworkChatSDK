package com.chinayin.wework.chatdata.model.messagetype;

import com.chinayin.wework.chatdata.model.messagetype.item.TypeMixedItem;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 混合消息
 */
@Data
public class TypeMixed {

    /**
     * 混合消息列表
     */
    private List<TypeMixedItem> item;
}