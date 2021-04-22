package com.kot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kbot.KbotApplication;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.config.BotContainer;
import com.kbot.entity.pcr.CardPool;
import com.kbot.service.CommandHandleService;
import com.kbot.utils.FileUtil;
import org.apache.http.client.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.smartcardio.Card;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class PcrTest {

    @Autowired
    private CardPool maxCardPool;

    @Test
    public void cardPoolTest(){
        String jsonStr = FileUtil.readJsonFile("./blcardpool.json");
        CardPool cardPool = JSON.parseObject(jsonStr, CardPool.class);
        System.out.println(cardPool);
    }

    @Test
    public void TimeTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sdf.parse("2021-04-18 23:01:01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
