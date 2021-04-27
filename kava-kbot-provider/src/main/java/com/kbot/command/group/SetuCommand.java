package com.kbot.command.group;

import com.google.common.collect.Lists;
import com.kbot.config.BotContainer;
import com.kbot.entity.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.service.ImageService;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: setu命令集合
 * <p>
 * Created by kris on 2021/4/23
 *
 * @author kris
 */
@Slf4j
@Service
public class SetuCommand implements GroupCommand {
    /**
     * 妹子图
     */
    private final String GIRL_MODE = "girlMode";
    /**
     * 二次元
     */
    private final String ACG_MODE = "acgMode";
    /**
     * setu
     */
    private final String SETU_MODE = "setuMode";
    /**
     * 错误
     */
    private final String ERR_MODE = "errMode";

    private final List<String> girlList = Lists.newArrayList("妹子");
    private final List<String> acgList = Lists.newArrayList("二次元");
    private final List<String> setuList = Lists.newArrayList("色图");

    private final long coolTime = 5*60*1000;

    @Autowired
    private ImageService imageService;
    @Autowired
    private CommandHandleService commandHandleService;
    @Autowired
    private ShareApiService gankApiService;
    @Autowired
    private BotContainer botContainer;

    @Override
    public CommandProperties properties() {
        List<String> alias = Lists.newArrayList();
        alias.addAll(girlList);
        alias.addAll(acgList);
        alias.addAll(setuList);
        return CommandProperties.builder()
                .name("girl")
                .type(1)
                .alias(Lists.newArrayList(alias))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Long aLong = botContainer.getImageCooling().get(subject.getId());
        if(aLong != null && System.currentTimeMillis() < aLong+coolTime){
            ArrayList<String> list = Lists.newArrayList("冲太多了，歇一会吧。", "注意身体。", "小撸怡情，大撸伤身，强撸灰飞烟灭。", "冷却中。。。剩余时间"+(aLong+coolTime - System.currentTimeMillis())/1000 + "s");
            return MessageUtils.newChain().plus(list.get(new Random().nextInt(list.size())));
        }
        String mode = getMode(commandHandleService.getContent(args));
        switch(mode){
            case GIRL_MODE:
                Message mode1 = girlMode(subject);
                botContainer.getImageCooling().put(subject.getId(),System.currentTimeMillis());
                return mode1;
            case ACG_MODE:
                return MessageUtils.newChain().plus("二次元妹子还在路上...");
            case SETU_MODE:
                return MessageUtils.newChain().plus("@黑宝 色图");
            default:
                break;
        }
        return MessageUtils.newChain()
                .plus(new PlainText("ue 听不懂你在说什么(⊙_⊙)?"));
    }

    private Message girlMode(Contact subject){
        return MessageUtils.newChain(imageService.sendImage4Online(subject,gankApiService.extract()));
    }

    /**
     * 根据信息获取是模式
     * @param message msg
     * @return mode
     */
    private String getMode(String message){
        if(girlList.contains(message)){
            return GIRL_MODE;
        }else if(acgList.contains(message)){
            return ACG_MODE;
        }else if (setuList.contains(message)){
            return SETU_MODE;
        }
        return ERR_MODE;
    }

}
