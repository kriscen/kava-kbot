package com.kot;

import com.kbot.KbotApplication;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.config.BotContainer;
import com.kbot.constant.FilePathConstant;
import com.kbot.service.CommandHandleService;
import com.kbot.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class CommandTest {

    @Autowired
    private CommandHandleService commandHandleService;

    @Autowired
    private BotContainer botContainer;


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
