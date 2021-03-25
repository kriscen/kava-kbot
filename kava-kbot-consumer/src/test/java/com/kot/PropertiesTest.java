package com.kot;

import com.kbot.KbotApplication;
import com.kbot.config.BotProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class PropertiesTest {
    @Autowired
    private BotProperties botProperties;


    @Test
    public void ymlTest(){
        System.out.println(botProperties);
    }

    @Test
    public void filePathTest(){
//        try {
//            String p = "src/main/resources/file/1";
//            BufferedReader reader = new BufferedReader(new FileReader(p));
//            String line = null;
//            while ((line = reader.readLine())!=null){
//                System.out.println(line);
//            }
//
//        } catch (FileNotFoundException e) {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//        }
    }
    @Test
    public void guavaTest() throws Exception {
//        BufferedInputStream bufferedInputStream = (BufferedInputStream) Resources.getResource("static/files/1").getContent();
//        byte[] bs = new byte[1024];
//        while (bufferedInputStream.read(bs) != -1) {
//            System.out.println(new String(bs));
//        }
    }
}
