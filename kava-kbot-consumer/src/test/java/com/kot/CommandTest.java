package com.kot;

import com.kbot.KbotApplication;
import com.kbot.command.BaseCommand;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.config.BotContainer;
import com.kbot.constant.FilePathConstant;
import com.kbot.entity.CommandProperties;
import com.kbot.service.CommandHandleService;
import com.kbot.utils.FileUtil;
import com.kbot.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class CommandTest {

    @Autowired
    private CommandHandleService commandHandleService;

    @Autowired
    private BotContainer botContainer;

    @Test
    public void adminTest(){
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        Map<String, BaseCommand> commandMap = context.getBeansOfType(BaseCommand.class);
        StringBuilder sb = new StringBuilder();
        commandMap.forEach((key, c) -> {
            if(!"admin".equals(c.properties().getName()) && !"test".equals(c.properties().getName())){
                StringBuilder tmp = new StringBuilder();
                CommandProperties properties = c.properties();
                tmp.append("指令：").append(properties.getName()).append(" ")
                        .append("说明：").append(properties.getDesc()).append(" ")
                        .append("具体指令：");
                properties.getAlias().forEach(alia -> tmp.append(alia).append(","));
                sb.append(tmp.substring(0,tmp.length() - 1));
                sb.append("\n");
            }
        });
        System.out.println(sb.toString());
    }

    @Test
    public void catrotTest(){
        File tarotFile = new File(FileUtil.getFilePath(FilePathConstant.CATROT_FILE_MODE + "/catrot.txt"));
        //创建读取器
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(tarotFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //逐行读取文件
        String freeTimeStr = null;
        //跳过第一行 是标识的数据来源
        try {
            reader.readLine();
            while ((freeTimeStr = reader.readLine()) != null) {
                System.out.println(freeTimeStr);
            }
        }catch (Exception e){

        }finally {
            //关闭读取器
            try {
                reader.close();
            } catch (IOException e) {

            }
        }
    }

    @Test
    public void commandTest(){
        String msg = ".version";
        Map<String, EverywhereCommand> everywhereCommands = botContainer.getEverywhereCommands();
        System.out.println(commandHandleService.getCommand(msg,everywhereCommands));
    }
}
