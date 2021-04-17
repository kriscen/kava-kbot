package com.kot;

import com.kbot.KbotApplication;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.config.BotContainer;
import com.kbot.service.CommandHandleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class CommandTest {

    @Autowired
    private CommandHandleService commandHandleService;

    @Autowired
    private BotContainer botContainer;

    @Test
    public void commandTest(){
        String msg = ".version";
        Map<String, EverywhereCommand> everywhereCommands = botContainer.getEverywhereCommands();
        System.out.println(commandHandleService.getCommand(msg,everywhereCommands));
    }
}
