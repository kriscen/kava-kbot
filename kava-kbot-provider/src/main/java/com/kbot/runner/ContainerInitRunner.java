package com.kbot.runner;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kbot.command.BaseCommand;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.command.friend.FriendCommand;
import com.kbot.command.group.GroupCommand;
import com.kbot.command.grouptempmessage.GroupTempMessageCommand;
import com.kbot.config.BotContainer;
import com.kbot.config.BotProperties;
import com.kbot.entity.CommandProperties;
import com.kbot.entity.KrisBot;
import com.kbot.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Program Name:kava-kbot
 * <p>
 * Description: 本地文本加载
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Order(1)
@Component
@Slf4j
public class ContainerInitRunner implements ApplicationRunner {

    @Autowired
    private BotProperties botProperties;
    @Autowired
    private BotContainer botContainer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        botContainer.setEverywhereCommands(getEverywhereCommand());
        botContainer.setFriendCommands(getFriendCommand());
        botContainer.setGroupCommands(getGroupCommand());
        botContainer.setGroupTempMessageCommands(getGroupTempMessageCommand());
        botContainer.setGroupLastMsg(Maps.newHashMap());
        botContainer.setCommandHead(getCommandHead());
        botContainer.setBlackList(Lists.newArrayList());
    }

    private Set<String> getCommandHead(){
        Set<String> set = Sets.newHashSet();
        if(botProperties.getForceCall()){
            set.add(botProperties.getNickname());
        }else {
            set.add(botProperties.getCommandPrefix());
        }
        return set;
    }

    private Map<String, EverywhereCommand> getEverywhereCommand(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, EverywhereCommand> commandMap = context.getBeansOfType(EverywhereCommand.class);
        log.info("EverywhereCommand....{}个",commandMap.size());
        Map<String, EverywhereCommand> map = new HashMap<>(4);
        if(commandMap == null || commandMap.isEmpty()){
            return map;
        }
        commandMap.forEach((key, c) -> {
            CommandProperties properties = c.properties();
            map.put(properties.getName(), c);
            properties.getAlias().forEach(alia -> map.put(alia, c));
        });
        return map;
    }

    private Map<String, FriendCommand> getFriendCommand(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, FriendCommand> commandMap = context.getBeansOfType(FriendCommand.class);
        log.info("FriendCommand....{}个",commandMap.size());
        Map<String, FriendCommand> map = new HashMap<>(4);
        if(commandMap == null || commandMap.isEmpty()){
            return map;
        }
        commandMap.forEach((key, c) -> {
            CommandProperties properties = c.properties();
            map.put(properties.getName().toLowerCase(), c);
            properties.getAlias().forEach(alia -> map.put(alia, c));
        });
        return map;
    }

    private Map<String, GroupCommand> getGroupCommand(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, GroupCommand> commandMap = context.getBeansOfType(GroupCommand.class);
        log.info("GroupCommand....{}个",commandMap.size());
        Map<String, GroupCommand> map = new HashMap<>(4);
        if(commandMap == null || commandMap.isEmpty()){
            return map;
        }
        commandMap.forEach((key, c) -> {
            CommandProperties properties = c.properties();
            map.put(properties.getName(), c);
            properties.getAlias().forEach(alia -> map.put(alia, c));
        });
        return map;
    }

    private Map<String, GroupTempMessageCommand> getGroupTempMessageCommand(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, GroupTempMessageCommand> commandMap = context.getBeansOfType(GroupTempMessageCommand.class);
        log.info("GroupTempMessageCommand....{}个",commandMap.size());
        Map<String, GroupTempMessageCommand> map = new HashMap<>(4);
        if(commandMap == null || commandMap.isEmpty()){
            return map;
        }
        commandMap.forEach((key, c) -> {
            CommandProperties properties = c.properties();
            map.put(properties.getName(), c);
            properties.getAlias().forEach(alia -> map.put(alia, c));
        });
        return map;
    }

}
