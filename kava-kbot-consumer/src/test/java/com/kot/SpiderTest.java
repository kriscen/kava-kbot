package com.kot;

import com.kbot.KbotApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class SpiderTest {

    @Test
    public void weiboTest() throws IOException {
        String url = "https://weibo.com/p/1002062286908003/home?from=page_100206&mod=TAB&is_all=1";
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.getElementsByAttribute("username"));
        System.out.println(".......+");
    }

}
