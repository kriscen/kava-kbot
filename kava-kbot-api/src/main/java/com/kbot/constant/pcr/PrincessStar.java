package com.kbot.constant.pcr;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Program Name:kava-kbot
 * <p>
 * Description: 卡池角色类型
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@AllArgsConstructor
public enum PrincessStar {
    /**
     * up
     */
    STAR_UP(3),
    /**
     * 3星
     */
    STAR_3(3),
    /**
     * 2星
     */
    STAR_2(2),
    /**
     * 1星
     */
    STAR_1(1);
    @Getter
    private Integer starNUm;
}
