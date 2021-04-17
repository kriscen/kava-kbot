package com.kbot.command.friend;

import com.kbot.entity.CommandProperties;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.*;
import java.util.Arrays;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 个人信息测试
 * <p>
 * Created by kris on 2021/3/29
 *
 * @author kris
 */
public class FriendTestCommand implements FriendCommand {
    private final String VERSION = "version";
    private final String STRING_MODE = "mode1";
    private final String IMAGE_MODE = "mode2";

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("测试模式")
                .type(1)
                .alias(Arrays.asList(VERSION,STRING_MODE,IMAGE_MODE))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Friend friend = (Friend) sender;
        String string = messageChain.toString();


        return null;
    }



    private Image imageMode(){
        return ExternalResource.uploadAsImage((File) null,null);
    }
}
