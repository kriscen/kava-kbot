package com.kbot.command;

import com.kbot.entity.CommandProperties;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 顶层command
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
public interface BaseCommand {
    /**
     * 构造指令名称以及别称
     * 不区分大小写
     * @return 指令名称对象
     */
    CommandProperties properties();

    /**
     * 具体业务执行入口
     * @param sender    消息发送人
     * @param args      指令追加参数
     * @param messageChain  消息对象 第一个元素一定为 [MessageSource], 存储此消息的发送人, 发送时间, 收信人, 消息 id 等数据. 随后的元素为拥有顺序的真实消息内容.
     * @param subject   消息主体
     * @return 回复的消息内容，返回null不做处理
     */
    Message execute(User sender, String args, MessageChain messageChain, Contact subject);
}
