package com.kbot.entity.pcr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 卡池对象
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardPool {
    /**
     * up角色概率
     */
    private Integer upProb;
    /**
     * 三星概率
     */
    private Integer s3Prob;
    /**
     * 二星概率
     */
    private Integer s2Prob;
    /**
     * up角色
     */
    private List<String> up;
    /**
     * 描述
     */
    private String comment;
    /**
     * 三星卡池
     */
    private List<String> star3;
    /**
     * 其余三星卡池？
     */
    private List<String> otherNormalStar3;
    /**
     * 二星卡池
     */
    private List<String> star2;
    /**
     * 一星卡池
     */
    private List<String> star1;
}
