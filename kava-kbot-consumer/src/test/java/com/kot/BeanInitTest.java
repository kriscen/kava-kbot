package com.kot;

import com.kbot.KbotApplication;
import com.kbot.command.BaseCommand;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.entity.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class BeanInitTest {

    @Autowired
    private CommandHandleService commandHandleService;

    @Test
    public void BeanInitTest(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, EverywhereCommand> commandMap = context.getBeansOfType(EverywhereCommand.class);
        Map<String, BaseCommand> map = new HashMap<>();
        commandMap.entrySet().forEach(t->{
            EverywhereCommand c = t.getValue();
            CommandProperties properties = c.properties();
            map.put(properties.getName(),c);
            properties.getAlias().forEach(alia->map.put(alia,c));
        });
        System.out.println(map.toString());
    }

    @Test
    public void commandTest(){
        String msg = "ue十连";
        System.out.println(commandHandleService.getContent(msg));
    }
}
