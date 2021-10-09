package com.kot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.kbot.KbotApplication;
import com.kbot.constant.ShareApiConstant;
import com.kbot.entity.shareApi.GankResult;
import com.kbot.service.ImageService;
import com.kbot.service.ShareApiService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KbotApplication.class)
public class ShareApiTest {
    @Autowired
    private ShareApiService bcrCalendarApiService;
    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void bcrTest() {
        System.out.println(restTemplate);
    }

    @Test
    public void yiyanTest() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.TEN_YIYAN_URL);
        try {
            get.addHeader(":authority","tenapi.cn");
            get.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String word = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(word);
            }
        } catch (Exception e) {
        } finally {
            IOUtils.close(client);
        }
//        ResponseEntity<String> entity = restTemplate.getForEntity(ShareApiConstant.TEN_YIYAN_URL, String.class);
//        System.out.println(entity.getStatusCode());
    }


    @Autowired
    private ImageService imageService;

    @Test
    public void newsTest() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.DAILY_NEWS_URL);
        try {
            get.addHeader("Referer", ShareApiConstant.DAILY_NEWS_REFERER);
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(),"utf-8");
                Map<String,String> hashMap = JSON.parseObject(json, HashMap.class);
                String url = hashMap.get("url");
                System.out.println(url);
            }
        } catch (Exception e) {
        } finally {
            IOUtils.close(client);
        }
//        ResponseEntity<String> entity = restTemplate.getForEntity(ShareApiConstant.TEN_YIYAN_URL, String.class);
//        System.out.println(entity.getStatusCode());
    }

    @Test
    public void gankTest() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.GANK_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                GankResult result = JSON.parseObject(json, GankResult.class);
                System.out.println(result.getData().get(0).getUrl());
            } else {
                System.out.println("no pic");
            }
        } catch (Exception e) {
            System.out.println("no pic");
        } finally {
            IOUtils.close(client);
        }
    }

    @Test
    public void CatTest() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.CAT_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            } else {
                System.out.println("no pic");
            }
        } catch (Exception e) {
            System.out.println("no pic");
        } finally {
            IOUtils.close(client);
        }
    }
}
