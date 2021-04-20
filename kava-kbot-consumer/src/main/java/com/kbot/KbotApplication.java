package com.kbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Program Name:kava-kbot
 * <p>
 * Description:
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@SpringBootApplication
@EnableScheduling
public class KbotApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(KbotApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KbotApplication.class);
    }
}
