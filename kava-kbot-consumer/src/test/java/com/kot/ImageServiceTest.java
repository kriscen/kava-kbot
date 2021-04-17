package com.kot;

import com.kbot.KbotApplication;
import com.kbot.command.everywhere.EverywhereCommand;
import com.kbot.config.BotContainer;
import com.kbot.service.CommandHandleService;
import com.kbot.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;


    @Test
    public void downloadTest(){
        String url = "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1986451467,394304688&fm=26&gp=0.jpg";
        String path = "./1.jpg";
        imageService.downloadImage(url,path);
    }

    @Test
    public void imagePathTest(){
        try {
            String url = ResourceUtils.getURL("classpath:").getPath();
            System.out.println(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
