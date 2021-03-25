package com.kbot.command.everywhere;

import com.kbot.config.BotContainer;
import com.kbot.entity.CommandProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 复读机
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Slf4j
@Service
public class RepeatWordService implements EverywhereCommand{

    @Autowired
    private BotContainer botContainer;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("cf")
                .type(3)
                .alias(Arrays.asList("复读机"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        log.info("repeat");
        return null;
    }
}
