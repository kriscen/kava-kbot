package com.kbot.scheduled;

import com.kbot.config.BotContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 每小时执行定时任务
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Component
public class HourScheduled {

    @Autowired
    private BotContainer botContainer;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void executeMin() throws Exception{
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour == 0){
            //每天开始清除数据
            botContainer.getCatrotList().clear();
            botContainer.getFortuneList().clear();
        }
        System.out.println("每小时执行");
    }
}
