package com.kbot.lottery.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Program Name: 
 * <p>
 * Description:
 * <p>
 * Created by kris on 2022/3/8
 *
 * @author kris
 */
@AllArgsConstructor
public enum TarotStatus {
    /**
     * 正位
     */
    UPRIGHT(1),
    /**
     * 逆位
     */
    REVERSED(2);
    @Getter
    private final Integer value;
}
