package com.kbot.command.everywhere;

import com.google.common.collect.Lists;
import com.kbot.command.BaseCommand;
import com.kbot.command.CommandProperties;
import com.kbot.utils.SpringContextUtil;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminCommand implements EverywhereCommand{

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("admin")
                .type(1)
                .alias(Lists.newArrayList("help","说明","指令"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, BaseCommand> commandMap = context.getBeansOfType(BaseCommand.class);
        StringBuilder sb = new StringBuilder();
        commandMap.forEach((key, c) -> {
            if(!"admin".equals(c.properties().getName()) && !"test".equals(c.properties().getName())){
                StringBuilder tmp = new StringBuilder();
                CommandProperties properties = c.properties();
                tmp.append("指令：").append(properties.getName()).append(" ")
                        .append("说明：").append(properties.getDesc()).append(" ")
                        .append("具体指令：");
                properties.getAlias().forEach(alia -> tmp.append(alia).append(","));
                sb.append(tmp.substring(0,tmp.length() - 1));
                sb.append("\n");
            }
        });
        return new MessageChainBuilder()
                .append(sb.toString())
                .build();
    }
}
