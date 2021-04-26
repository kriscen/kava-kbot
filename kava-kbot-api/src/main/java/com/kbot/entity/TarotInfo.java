package com.kbot.entity;

import lombok.Data;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 塔罗牌
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Data
public class TarotInfo {
    /**
     * 塔罗牌名称
     */
    private String name;
    /**
     * true 正位
     * false 逆位
     */
    private boolean status;
    /**
     * 图片名称
     */
    private String imgName;
    /**
     * 正位描述
     */
    private String normalDes;
    /**
     * 逆位描述
     */
    private String seDlamron;
    /**
     * 是否为猫罗牌
     */
    private boolean isCat = false;
}