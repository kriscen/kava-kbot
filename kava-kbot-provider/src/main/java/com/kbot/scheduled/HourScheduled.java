package com.kbot.scheduled;

import com.kbot.config.BotContainer;
import com.kbot.service.ShareApiService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import org.apache.http.client.utils.DateUtils;
import org.checkerframework.checker.units.qual.A;
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
    private Bot bot;

    @Autowired
    private BotContainer botContainer;

    @Autowired
    private ShareApiService bcrCalendarApiService;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void executeMin() throws Exception{
        GregorianCalendar calendar = new GregorianCalendar();
        //0-23 小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        clearContainer(hour);

        pcrActivity(hour);
        System.out.println("每小时执行");
    }

    /**
     * 每天发布pcr当天活动
     * @param hour 小时
     */
    private void pcrActivity(int hour){
        int workHour = 7;
        if(hour == workHour){
            //每天开始清除数据
            String s = bcrCalendarApiService.extract();
            specificGroup().sendMessage(s);
        }
    }

    /**
     * 清除每日容器缓存
     * @param hour 小时
     */
    private void clearContainer(int hour){
        if(hour == 0){
            //每天开始清除数据
            botContainer.getCatrotList().clear();
            botContainer.getFortuneList().clear();
        }
    }

    /**
     * 获取需要通知的组
     * @return
     */
    private Contact specificGroup(){
        //TODO 后边可以做权限
        long groupId = 0L;
        return bot.getGroup(groupId);
    }
}
