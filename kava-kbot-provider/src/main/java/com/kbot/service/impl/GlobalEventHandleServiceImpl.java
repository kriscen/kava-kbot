package com.kbot.service.impl;


import com.alibaba.fastjson.util.IOUtils;
import com.kbot.config.BotContainer;
import com.kbot.shareApi.constant.ShareApiConstant;
import com.kbot.bot.entity.GroupRepeat;
import com.kbot.service.GlobalEventHandleService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
        //复读
        repeat(args,messageChain,subject);
        //随机一句话
        randomWord(subject);
    }

    /**
     * 随机一语
     * @param subject 联系人
     * @return 是否执行
     */
    private boolean randomWord(Contact subject){
        //总概率
        int totalProb = 100;
        //触发概率线
        int yiyuProb = 2;
        //实际值
        int nextInt = new Random().nextInt(totalProb);
        if(nextInt<yiyuProb){
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(ShareApiConstant.TEN_YIYAN_URL);
            try {
                get.addHeader(":authority","tenapi.cn");
                get.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
                CloseableHttpResponse response = client.execute(get);
                if (200 == response.getStatusLine().getStatusCode()) {
                    String word = EntityUtils.toString(response.getEntity(), "UTF-8");
                    subject.sendMessage(MessageUtils.newChain(new PlainText(word)));
                    return true;
                }
            } catch (Exception e) {
                return false;
            }finally {
                IOUtils.close(client);
            }
        }

        return false;
    }

    /**
     * 复读
     * @param args 参数
     * @param messageChain 信息链
     * @param subject 联系人
     * @return 是否执行
     */
    private boolean repeat(String args, MessageChain messageChain,Contact subject){
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
            repeat.setTimes(1);
            botContainer.getRepeatGroup().put(subject.getId(),repeat);
            subject.sendMessage(messageChain);
            return true;
        }
        return false;
    }

    private int getRepeatProb(int times,int initProb){
        for (int i = 0; i < times; i++) {
            initProb *= 2;
        }
        return initProb;
    }
}
