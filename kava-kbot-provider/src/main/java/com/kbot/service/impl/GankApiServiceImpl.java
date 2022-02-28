package com.kbot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.kbot.shareApi.constant.ShareApiConstant;
import com.kbot.shareApi.entity.GankResult;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: gank api service
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Slf4j
@Service("gankApiService")
public class GankApiServiceImpl implements ShareApiService {
    @Override
    public String extract() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.GANK_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                GankResult result = JSON.parseObject(json, GankResult.class);
                return result.getData().get(0).getUrl();
            } else {
                System.out.println("no pic");
            }
        } catch (Exception e) {
            System.out.println("no pic");
        }finally {
            IOUtils.close(client);
        }
        return null;
    }
}
