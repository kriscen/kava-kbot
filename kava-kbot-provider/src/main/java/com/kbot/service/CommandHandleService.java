package com.kbot.service;

import com.kbot.command.BaseCommand;

import java.util.Map;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 命令处理
 * <p>
 * Created by kris on 2021/3/25
 *
 * @author kris
 */
public interface CommandHandleService {

    /**
     * 判断是否为触发信息
     * @param msg
     * @return
     */
    boolean isCommand(String msg);

    /**
     * 根据输入信息返回匹配的指令
     * @param msg msg
     * @param commandContainer command
     * @return
     */
    BaseCommand getCommand(String msg, Map<String,? extends BaseCommand> commandContainer);

    /**
     * 获取除了指令以外的内容
     * @param msg
     * @return
     */
    String getContent(String msg);
}
