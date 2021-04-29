package com.kbot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.kbot.config.BotProperties;
import com.kbot.constant.ShareApiConstant;
import com.kbot.entity.shareApi.LoliconResult;
import com.kbot.service.LoliconApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: lolicon service
 * <p>
 * Created by kris on 2021/4/29
 *
 * @author kris
 */
@Slf4j
@Service("loliconApiService")
public class LoliconApiServiceImpl implements LoliconApiService {

    @Autowired
    private BotProperties botProperties;

    @Override
    public String getLoliconImage(int r18, String searchWord) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(buildUrl(r18,searchWord));
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                LoliconResult result = JSON.parseObject(json, LoliconResult.class);
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

    private String buildUrl(int r18, String keyword){
        String url = ShareApiConstant.LOLICON_URL+"?apikey="+botProperties.getLoliconKey()+"&r18="+r18;
        if(StringUtils.isNotBlank(keyword)){
            url = url + "&keyword="+keyword;
        }
        return url;
    }
}
