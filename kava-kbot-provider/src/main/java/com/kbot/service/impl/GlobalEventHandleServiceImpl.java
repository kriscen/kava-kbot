package com.kbot.service.impl;


import com.kbot.config.BotContainer;
import com.kbot.entity.GroupRepeat;
import com.kbot.service.GlobalEventHandleService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Program Name:
 * <p>
 * Description: 
 * <p>
 * Created by kris on 2021/4/27
 *
 * @author kris
 */
@Slf4j
@Service
public class GlobalEventHandleServiceImpl implements GlobalEventHandleService {

    private final int repeatProb = 1000;

    @Autowired
    private BotContainer botContainer;

    @Override
    public void execute(User sender, String args, MessageChain messageChain, Contact subject) {
        repeat(args,subject);
    }

    private void repeat(String args, Contact subject){
        //初始概率
        int initProb = 2;
        GroupRepeat repeat = botContainer.getRepeatGroup().get(subject.getId());
        if(repeat == null){
            repeat = GroupRepeat.builder().repeatStr(args).times(1).build();
            botContainer.getRepeatGroup().put(subject.getId(),repeat);
        }else {
            if(args.equals(repeat.getRepeatStr())){
                repeat.setTimes(repeat.getTimes()+1);
            }else{
                repeat.setRepeatStr(args);
            }
        }
        botContainer.getRepeatGroup().put(subject.getId(),repeat);
        int prob = getRepeatProb(repeat.getTimes(), initProb);
        int i = new Random().nextInt(repeatProb);
        if(i<prob){
            subject.sendMessage(MessageUtils.newChain().plus(args));
        }
    }

    private int getRepeatProb(int times,int initProb){
        for (int i = 0; i < times; i++) {
            initProb *= 2;
        }
        return initProb;
    }
}
