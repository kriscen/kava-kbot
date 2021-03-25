package com.kbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: bot配置文件
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Data
@Component
@PropertySource(value = "classpath:bot.properties")
public class BotProperties {

    @Value("${bot.qq}")
    private Long qq;

    @Value("${bot.password}")
    private String password;

    @Value("${bot.deviceInfoPath}")
    private String deviceInfoPath;

    @Value("${bot.envSwitch}")
    private Boolean envSwitch;

    @Value("${bot.forceCall}")
    private Boolean forceCall;

    @Value("${bot.master}")
    private Long master;

    @Value("${bot.nickname}")
    private String nickname;

    @Value("${bot.commandPrefix}")
    private String commandPrefix;
}
