package com.kbot.runner;


import com.kbot.entity.KrisBot;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Program Name:kava-kbot
 * <p>
 * Description: 本地文本加载
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Order(2)
@Component
@Slf4j
public class BotRunner implements ApplicationRunner {

    @Autowired
    private Bot bot;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!(bot instanceof KrisBot)){
            new Thread(() -> bot.join()).start();
        }
    }

}
