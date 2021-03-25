package com.kbot.runner;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Program Name:kava-kbot
 * <p>
 * Description: 本地文本加载
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Order(3)
@Component
@Slf4j
public class FileLoadRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

}
