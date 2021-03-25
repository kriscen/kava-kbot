package com.kbot.constant;

import lombok.Data;
import lombok.Getter;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 模式类型
 * <p>
 * Created by kris on 2021/3/21
 *
 * @author kris
 */
public enum MagicTypeEnum {
    /**
     * 字段全匹配
     */
    WHOLE_WORD,
    /**
     * 正则匹配
     */
    REGULAR_WORD,
    /**
     * 不需要匹配，全部符合
     */
    ANYONE_WORD;

}
