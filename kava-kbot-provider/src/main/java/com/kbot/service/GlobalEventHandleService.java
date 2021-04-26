package com.kbot.service;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * Program Name:
 * <p>
 * Description: 
 * <p>
 * Created by kris on 2021/4/27
 *
 * @author kris
 */
public interface GlobalEventHandleService {
    void execute(User sender, String args, MessageChain messageChain, Contact subject);
}
