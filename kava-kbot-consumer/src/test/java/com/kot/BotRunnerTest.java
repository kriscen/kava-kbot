package com.kot;

import com.kbot.KbotApplication;
import com.kbot.config.BotProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class BotRunnerTest {

    @Autowired
    private BotProperties botProperties;


    @Test
    public void ymlTest(){
//        Bot bot = BotFactory.INSTANCE.newBot(botProperties.getQq(), botProperties.getPassword(), new BotConfiguration() {{
//            // 配置，例如：
//            fileBasedDeviceInfo(botProperties.getDeviceInfoPath());
//            setProtocol(MiraiProtocol.ANDROID_PHONE);
//        }});
//        bot.login();
        System.out.println(new BigDecimal("0.0").equals(BigDecimal.ZERO));
    }
}
