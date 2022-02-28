package com.kbot.config;

import com.alibaba.fastjson.JSON;
import com.kbot.bot.constant.FilePathConstant;
import com.kbot.bot.entity.KrisBot;
import com.kbot.lottery.entity.CardPool;
import com.kbot.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: pcr相关bean初始化
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Configuration
public class PcrBeanConfig {


    @Autowired
    private Bot bot;

    @Bean()
    public CardPool maxCardPool(){
        if(bot instanceof KrisBot){
            return CardPool.builder().comment("maxCardPool").build();
        }else{
            String jsonStr = FileUtil.readJsonFile(FileUtil.getFilePath(FilePathConstant.MAX_CARD_POOL_PATH));
            return JSON.parseObject(jsonStr, CardPool.class);
        }
    }
    @Bean()
    public CardPool blCardPool(){
        if(bot instanceof KrisBot){
            return CardPool.builder().comment("blCardPool").build();
        }else{
            String jsonStr = FileUtil.readJsonFile(FileUtil.getFilePath(FilePathConstant.BL_CARD_POOL_PATH));
            return JSON.parseObject(jsonStr, CardPool.class);
        }
    }
}
