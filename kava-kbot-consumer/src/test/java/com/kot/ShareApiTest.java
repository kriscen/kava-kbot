package com.kot;

import com.alibaba.fastjson.util.IOUtils;
import com.kbot.KbotApplication;
import com.kbot.constant.ShareApiConstant;
import com.kbot.service.ShareApiService;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class ShareApiTest {
    @Autowired
    private ShareApiService bcrCalendarApiService;

    @Test
    public void bcrTest(){
        System.out.println(bcrCalendarApiService.extract());
    }

    @Test
    public void yiyanTest(){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.TEN_YIYAN_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String word = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(word);
            }
        } catch (Exception e) {
        }finally {
            IOUtils.close(client);
        }
    }
}