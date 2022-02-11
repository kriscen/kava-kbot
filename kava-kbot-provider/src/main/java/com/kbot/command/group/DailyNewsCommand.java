package com.kbot.command.group;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kbot.config.BotContainer;
import com.kbot.constant.ShareApiConstant;
import com.kbot.entity.CommandProperties;
import com.kbot.service.ImageService;
import com.kbot.service.ShareApiService;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Program Name: 
 * <p>
 * Description: 每日新闻
 * <p>
 * Created by kris on 2021/12/7
 *
 * @author kris
 */
@Component
public class DailyNewsCommand implements GroupCommand{

    @Autowired
    private ImageService imageService;
    @Autowired
    private ShareApiService dailyNewsApiService;


    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("dailyNews")
                .type(1)
                .desc("每日新闻")
                .alias(Lists.newArrayList("每日新闻"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        String url = dailyNewsApiService.extract();
        Map<String,String> params = Maps.newHashMap();
        params.put("Referer", ShareApiConstant.DAILY_NEWS_REFERER);
        return MessageUtils.newChain().plus(imageService.sendImage4Online(sender,url,params));
    }


}
