package com.kot;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.kbot.KbotApplication;
import com.kbot.entity.CardPool;
import com.kbot.service.CommandHandleService;
import com.kbot.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class PcrTest {

    @Autowired
    private CardPool maxCardPool;
    @Autowired
    private CommandHandleService commandHandleService;

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
    private Pattern singlePattern = Pattern.compile("(roll)(\\s)*[\\d+]");
    private Pattern multiPattern = Pattern.compile("(roll)[\\s]*(d)[\\d+](r)[\\d+]");
    @Test
    public void diceTest(){
        System.out.println(System.currentTimeMillis());
    }

    private String getDiceResult(String content){
        String d = "d";
        String r = "r";
        String command = content.substring("roll".length()).trim();
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(command)){
            Random random = new Random();
            if(multiPattern.matcher(content).find()){
                String s = command.substring(command.indexOf(d)+1, command.indexOf(r));
                int i = Integer.parseInt(s);
                String s2 = command.substring(command.indexOf(r)+1);
                int j = Integer.parseInt(s2);
                List<Integer> list = Lists.newArrayList();
                int count = 0;
                for (int k = 0; k < i; k++) {
                    int rd = random.nextInt(j)+1;
                    list.add(rd);
                    count += rd;
                }
                return sb.append(count).append(list.toString()).toString();
            }else if(singlePattern.matcher(content).find()){
                int i = Integer.parseInt(command);
                int nextInt = random.nextInt(i)+1;
                return nextInt+"";
            }
        }
        return null;
    }
}
