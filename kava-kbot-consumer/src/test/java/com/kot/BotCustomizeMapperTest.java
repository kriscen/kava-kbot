package com.kot;

import com.kbot.KbotApplication;
import com.kbot.bot.entity.BotCustomize;
import com.kbot.bot.mapper.BotCustomizeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class BotCustomizeMapperTest {

    @Autowired
    private BotCustomizeMapper botCustomizeMapper;


    @Test
    public void findOne() throws IOException {
        System.out.println(botCustomizeMapper);
        BotCustomize botCustomize = botCustomizeMapper.selectById(1);
        System.out.println(botCustomize);
    }
}
