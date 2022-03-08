package com.kbot.config;

import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.command.friend.FriendCommand;
import com.kbot.command.group.GroupCommand;
import com.kbot.command.grouptempmessage.GroupTempMessageCommand;
import com.kbot.bot.entity.GroupRepeat;
import com.kbot.lottery.entity.tarot.TarotInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 容器初始配置
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Slf4j
@Data
public class BotContainer {

    /**
     * 保存对象最后的一句话
     */
    private Map<Integer,String> groupLastMsg;

    /**
     * everywhereCommand
     */
    private Map<String, EverywhereCommand> everywhereCommands;

    /**
     * friendCommand
     */
    private Map<String, FriendCommand> friendCommands;

    /**
     * groupCommand
     */
    private Map<String, GroupCommand> groupCommands;

    /**
     * groupTempMessageCommand
     */
    private Map<String, GroupTempMessageCommand> groupTempMessageCommands;

    /**
     * 命令头集合
     */
    private Set<String> commandHead;

    /**
     * 黑名单集合
     */
    private List<Long> blackList;

    /**
     * 猫罗牌文本
     */
    private List<TarotInfo> catrotTextList;

    /**
     * 每日猫罗牌
     */
    private Map<Long,TarotInfo> catrotList;

    /**
     * 每日运势
     */
    private Map<Long,Integer> fortuneList;

    /**
     * 每个群组的复读概率
     */
    private Map<Long, GroupRepeat> repeatGroup;
    /**
     * 图片冷却时间
     */
    private Map<Long,Long> imageCooling;
    /**
     * 图片冷却时间
     */
    private Map<Long,Long> catCooling;
}
