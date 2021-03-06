package com.kbot.config;

import com.kbot.bot.entity.KrisBot;
import com.kbot.event.GroupEvents;
import com.kbot.event.MessageEvents;
import com.kbot.utils.FileUtil;
import kotlin.jvm.functions.Function1;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: bot初始化
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@Configuration
@Slf4j
public class BotConfig {

    @Autowired
    private BotProperties botProperties;

    @Autowired
    private Bot bot;

    @Bean
    public Bot bot(){
        if(botProperties.getEnvSwitch()){
            log.info("mirai bot初始化.......");
            Bot bot = BotFactory.INSTANCE.newBot(botProperties.getQq(), botProperties.getPassword(), new BotConfiguration() {{
                // 配置，例如：
                fileBasedDeviceInfo(FileUtil.getFilePath(botProperties.getDeviceInfoPath()));
                setProtocol(MiraiProtocol.ANDROID_PHONE);
            }});
            Function1<Bot, DeviceInfo> deviceInfo = bot.getConfiguration().getDeviceInfo();
            DeviceInfo info = deviceInfo.invoke(bot);
            log.info("deviceInfo.."+info.getImei());
            bot.login();
            return bot;
        }else{
            log.info("kris bot初始化.......");
            return new KrisBot();
        }
    }
    private void checkDeviceInfo(){

    }

    @Bean
    public MessageEvents messageEvents(){
        MessageEvents messageEvents = new MessageEvents();
        if(!(bot instanceof KrisBot)){
            bot.getEventChannel().registerListenerHost(messageEvents);
        }
        return messageEvents;
    }

    @Bean
    public GroupEvents groupEvents(){
        GroupEvents groupEvents = new GroupEvents();
        if(!(bot instanceof KrisBot)){
            bot.getEventChannel().registerListenerHost(groupEvents);
        }
        return groupEvents;
    }

    @Bean
    public BotContainer botContainer(){
        BotContainer container = new BotContainer();
        return container;
    }



}
