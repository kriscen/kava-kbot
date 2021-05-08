package com.kbot.command.group;

import com.google.common.collect.Lists;
import com.kbot.config.BotContainer;
import com.kbot.entity.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.service.ImageService;
import com.kbot.service.LoliconApiService;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
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
     * greedy
     */
    private final String GREEDY_MODE = "greedyMode";
    /**
     * 错误
     */
    private final String ERR_MODE = "errMode";

    private final List<String> girlList = Lists.newArrayList("妹子");
    private final List<String> acgList = Lists.newArrayList("二次元");
    private final List<String> setuList = Lists.newArrayList("色图");
    private final List<String> greedyList = Lists.newArrayList("不够色","我觉得不行");

    private final long coolTime = 2*60*1000;

    @Autowired
    private ImageService imageService;
    @Autowired
    private CommandHandleService commandHandleService;
    @Autowired
    private ShareApiService gankApiService;
    @Autowired
    private BotContainer botContainer;
    @Autowired
    private LoliconApiService loliconApiService;

    @Override
    public CommandProperties properties() {
        List<String> alias = Lists.newArrayList();
        alias.addAll(girlList);
        alias.addAll(acgList);
        alias.addAll(setuList);
        return CommandProperties.builder()
                .name("girl")
                .type(1)
                .desc("setu相关")
                .alias(Lists.newArrayList(alias))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        String mode = getMode(commandHandleService.getContent(args));
        if(GIRL_MODE.equals(mode) || ACG_MODE.equals(mode)){
            Long aLong = botContainer.getImageCooling().get(subject.getId());
            if(aLong != null && System.currentTimeMillis() < aLong+coolTime){
                ArrayList<String> list = Lists.newArrayList("冲太多了，歇一会吧。", "注意身体。", "小撸怡情，大撸伤身，强撸灰飞烟灭。",
                        "冷却中。。。剩余时间"+(aLong+coolTime - System.currentTimeMillis())/1000 + "s");
                return MessageUtils.newChain().plus(list.get(new Random().nextInt(list.size())));
            }
        }
        switch(mode){
            case GIRL_MODE:
                Message mode1 = girlMode(subject);
                botContainer.getImageCooling().put(subject.getId(),System.currentTimeMillis());
                return mode1;
            case ACG_MODE:
                Message acgMode = acgMode(subject);
                botContainer.getImageCooling().put(subject.getId(),System.currentTimeMillis());
                return acgMode;
            case SETU_MODE:
                return MessageUtils.newChain().plus("@黑宝 色图");
            default:
                break;
        }
        return MessageUtils.newChain()
                .plus(new PlainText("ue 听不懂你在说什么(⊙_⊙)?"));
    }

    private Message setuMode(Contact subject) {
        return MessageUtils.newChain(FlashImage.from(imageService.sendImage4Online(subject, loliconApiService.getLoliconImage(1, null))));
    }

    private Message acgMode(Contact subject) {
        return MessageUtils.newChain(imageService.sendImage4Online(subject,loliconApiService.getLoliconImage(0,null)));
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
        }else if (greedyList.contains(message)){
            return GREEDY_MODE;
        }
        return ERR_MODE;
    }

}
