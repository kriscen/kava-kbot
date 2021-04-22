package com.kot;

import com.kbot.KbotApplication;
import com.kbot.constant.FilePathConstant;
import com.kbot.service.ImageService;
import com.kbot.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void copy(){
        File file = new File(FileUtil.getFilePath(FilePathConstant.PCR_AVATAR_SMALL_IMAGE_MODE));
        File[] files = file.listFiles();
        for (File source : files) {
            System.out.println(source.getName());
//                Thumbnails.of(source)
//                        .size(50, 50)
//                        .toFile("D:/image/"+source.getName());
        }
    }

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
