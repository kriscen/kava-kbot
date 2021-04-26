package com.kbot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.kbot.constant.ShareApiConstant;
import com.kbot.entity.shareApi.BcrCalendar;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: bcrCalendar api service
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Slf4j
@Service("bcrCalendarApiService")
public class BcrCalendarApiServiceImpl implements ShareApiService {
    @Override
    public String extract() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(ShareApiConstant.BCR_CALENDAR_URL);
        try {
            CloseableHttpResponse response = client.execute(get);
            if (200 == response.getStatusLine().getStatusCode()) {
                String json = EntityUtils.toString(response.getEntity(), "UTF-8");
                List<BcrCalendar> result = JSON.parseArray(json, BcrCalendar.class);
                return activityForToday(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "查询活动失败";
        }finally {
            IOUtils.close(client);
        }
        return "查询活动失败";
    }

    /**
     * 查询当日的活动
     * @param list 活动list
     * @return 当日活动
     */
    private String activityForToday(List<BcrCalendar> list){
        if(list.isEmpty()){
            return "暂无活动";
        }
        List<String> activity = list.stream().filter(t -> {
            String startTime = t.getStart_time();
            String endTime = t.getEnd_time();
            String format = "yyyy/MM/dd HH:mm:ss";
            try {
                Date start = DateUtils.parseDate(startTime, format);
                Date end = DateUtils.parseDate(endTime, format);
                return start.getTime() < System.currentTimeMillis() && end.getTime() > System.currentTimeMillis();
            } catch (ParseException e) {
                return false;
            }
        }).map(BcrCalendar::getName).collect(Collectors.toList());
        if(activity.isEmpty()){
            return "暂无活动";
        }else {
            StringBuilder sb = new StringBuilder("今日活动:").append("\n");
            activity.forEach(t->sb.append(t).append("\n"));
            return sb.toString();
        }
    }

}
