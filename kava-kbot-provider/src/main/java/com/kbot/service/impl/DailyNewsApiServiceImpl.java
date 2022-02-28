package com.kbot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.kbot.shareApi.constant.ShareApiConstant;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: daily news api service
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Slf4j
@Service("dailyNewsApiService")
public class DailyNewsApiServiceImpl implements ShareApiService {
    @Override
    public String extract() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.DAILY_NEWS_URL);
        try {
            get.addHeader("Referer", ShareApiConstant.DAILY_NEWS_REFERER);
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(),"utf-8");
                Map<String,String> hashMap = JSON.parseObject(json, HashMap.class);
                return hashMap.get("url");
            }
        } catch (Exception e) {

        } finally {
            IOUtils.close(client);
        }
        return null;
    }
}
