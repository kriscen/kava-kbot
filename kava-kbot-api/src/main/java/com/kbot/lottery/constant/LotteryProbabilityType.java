package com.kbot.lottery.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Program Name:
 * <p>
 * Description: 抽奖概率类型
 * <p>
 * Created by kris on 2022/3/8
 *
 * @author kris
 */
@AllArgsConstructor
public enum LotteryProbabilityType {
    /**
     * 固定概率
     */
    PROBABILITY_FIX(1),
    /**
     * 浮动概率
     */
    PROBABILITY_FLOAT(2),
    /**
     * 等额概率
     */
    PROBABILITY_EQUALITY(3);
    @Getter
    private final Integer type;
}
