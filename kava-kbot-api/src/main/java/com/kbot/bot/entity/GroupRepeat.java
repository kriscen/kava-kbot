package com.kbot.bot.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 群组复读概率
 * <p>
 * Created by kris on 2021/4/26
 *
 * @author kris
 */
@Data
@Builder
public class GroupRepeat {
    public String repeatStr;
    public Integer times;
}
