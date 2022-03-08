package com.kbot.lottery.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Program Name: 
 * <p>
 * Description: 原神星级
 * <p>
 * Created by kris on 2022/3/8
 *
 * @author kris
 */
@AllArgsConstructor
public enum GenshinStar {
    /**
     * up
     */
    STAR_UP(5),
    /**
     * 5星
     */
    STAR_5(5),
    /**
     * 4星
     */
    STAR_4(4),
    /**
     * 3星
     */
    STAR_3(3);
    @Getter
    private final Integer starNum;
}
