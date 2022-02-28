package com.kbot.bot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kbot.common.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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
@ToString
@TableName("bot_customize")
public class BotCustomize extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
