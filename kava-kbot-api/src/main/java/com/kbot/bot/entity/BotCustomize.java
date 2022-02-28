package com.kbot.bot.entity;

import lombok.Data;

/**
 * Program Name:
 * <p>
 * Description: bot定制项
 * <p>
 * Created by kris on 2022/2/28
 *
 * @author kris
 */
@Data
public class BotCustomize {
    /**
     * bot id
     */
    private Long botId;
    /**
     * bot 简称
     */
    private String nickname;
    /**
     * 是否强制使用简称呼唤
     */
    private Boolean forceCall;
    /**
     * master
     */
    private String master;

}
