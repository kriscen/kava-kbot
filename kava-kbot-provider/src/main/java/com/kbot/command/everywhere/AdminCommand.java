package com.kbot.command.everywhere;

import com.google.common.collect.Lists;
import com.kbot.entity.CommandProperties;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.stereotype.Service;

@Service
public class AdminCommand implements EverywhereCommand{

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("admin")
                .type(1)
                .alias(Lists.newArrayList(""))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        return null;
    }
}
