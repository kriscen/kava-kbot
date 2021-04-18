package com.kbot.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 每分钟执行定时任务
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Component
public class MinScheduled {

    @Scheduled(cron = "0 */6 * * * ?")
    public void minExecute() throws Exception{
        System.out.println("五分钟执行一遍");
    }

}
