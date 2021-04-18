package com.kbot.command.everywhere;

import com.kbot.entity.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.service.ImageService;
import com.kbot.utils.FileUtil;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class FriendTestCommand implements EverywhereCommand {
    private final String VERSION = "version";
    private final String STRING_MODE = "mode1";
    private final String IMAGE_MODE = "mode2";

    @Autowired
    private ImageService imageService;
    @Autowired
    private CommandHandleService commandHandleService;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("测试")
                .type(1)
                .alias(Arrays.asList(VERSION,STRING_MODE,IMAGE_MODE))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Friend friend = (Friend) sender;
        switch(commandHandleService.getContent(args)){
            case VERSION:
                return MessageUtils.newChain()
                        .plus("1.0-kava.start-SNAPSHOT");
            case STRING_MODE:
                return testString(friend);
            case IMAGE_MODE:
                return imageMode(friend);
            default:
                break;
        }
        return new MessageChainBuilder()
                .append("暂无指令")
                .build();
    }

    private Message testString(Friend friend){
        /*MessageChain chain = new MessageChainBuilder()
                .append(new PlainText("hello"))
                // 会被构造成 PlainText 再添加, 相当于上一行
                .append("word")
                .append(new At(friend.getId()))
                .build();*/
        return MessageUtils.newChain()
                .plus(new PlainText("hello"))
                .plus("word")
                .plus(new At(friend.getId()));
    }

    private Image imageMode(Friend friend){
        String path = FileUtil.getResourcePath() +  "static/image/ue.jpg";
        return imageService.sendImage4Local(friend,path);
    }
}
