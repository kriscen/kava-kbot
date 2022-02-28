package com.kbot.command.friend;

import com.google.common.collect.Maps;
import com.kbot.shareApi.constant.ShareApiConstant;
import com.kbot.command.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.service.ImageService;
import com.kbot.service.ShareApiService;
import com.kbot.utils.FileUtil;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

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
public class FriendTestCommand implements FriendCommand {
    private final String VERSION = "version";
    private final String STRING_MODE = "mode1";
    private final String IMAGE_MODE = "mode2";
    private final String TEST_MODE = "mode3";

    @Autowired
    private ImageService imageService;
    @Autowired
    private CommandHandleService commandHandleService;
    @Autowired
    private ShareApiService dailyNewsApiService;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("test")
                .type(1)
                .alias(Arrays.asList(VERSION,STRING_MODE,IMAGE_MODE,TEST_MODE))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        switch(commandHandleService.getContent(args)){
            case VERSION:
                return MessageUtils.newChain()
                        .plus("1.0-kava.start-SNAPSHOT");
            case STRING_MODE:
                return testString(sender);
            case IMAGE_MODE:
                return imageMode(sender);
            case TEST_MODE:
                return testMode(sender);
            default:
                break;
        }
        return new MessageChainBuilder()
                .append("暂无指令")
                .build();
    }

    private Message testString(User sender){
        /*MessageChain chain = new MessageChainBuilder()
                .append(new PlainText("hello"))
                // 会被构造成 PlainText 再添加, 相当于上一行
                .append("word")
                .append(new At(friend.getId()))
                .build();*/
        return MessageUtils.newChain()
                .plus(new PlainText("hello"))
                .plus("word")
                .plus(new At(sender.getId()));
    }

    private Image imageMode(User sender){
        String path = FileUtil.getFilePath("static/image/ue.jpg");
        return imageService.sendImage4Local(sender,path);
    }

    private Image testMode(User sender){
        String url = dailyNewsApiService.extract();
        System.out.println(url);
        Map<String,String> params = Maps.newHashMap();
        params.put("Referer", ShareApiConstant.DAILY_NEWS_REFERER);
        return imageService.sendImage4Online(sender,url,params);
    }
}
