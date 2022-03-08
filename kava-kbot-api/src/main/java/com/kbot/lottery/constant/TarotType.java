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
public enum TarotType {
    /**
     * 塔罗牌
     */
    TAROT(1),
    /**
     * 猫罗牌
     */
    CAT(2);
    @Getter
    private final Integer value;
}
