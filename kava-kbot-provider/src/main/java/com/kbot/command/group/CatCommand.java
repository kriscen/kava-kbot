package com.kbot.command.group;

import com.alibaba.fastjson.util.IOUtils;
import com.google.common.collect.Lists;
import com.kbot.config.BotContainer;
import com.kbot.shareApi.constant.ShareApiConstant;
import com.kbot.command.CommandProperties;
import com.kbot.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 猫罗牌
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Service
public class CatCommand implements GroupCommand {

    @Autowired
    private ImageService imageService;
    @Autowired
    private BotContainer botContainer;

    private final long coolTime = 1*30*1000;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("cat")
                .type(1)
                .desc("猫片")
                .alias(Lists.newArrayList("撸猫","猫猫","猫猫图"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Long aLong = botContainer.getCatCooling().get(subject.getId());
//        if(aLong != null && System.currentTimeMillis() < aLong+coolTime){
//            ArrayList<String> list = Lists.newArrayList("猫猫撸太多了，歇一会吧。", "少看点猫猫吧", "猫猫正在路上",
//                    "冷却中。。。剩余时间"+(aLong+coolTime - System.currentTimeMillis())/1000 + "s");
//            return MessageUtils.newChain().plus(list.get(new Random().nextInt(list.size())));
//        }
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.CAT_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                Image image = imageService.sendImage4Local(subject, bytes);
                botContainer.getCatCooling().put(subject.getId(),System.currentTimeMillis());
                return MessageUtils.newChain().plus(image);
            } else {
            }
        } catch (Exception e) {
        } finally {
            IOUtils.close(client);
        }
        return MessageUtils.newChain()
                .plus("没有猫猫了");
    }

}
