package com.kbot.config;

import com.alibaba.fastjson.JSON;
import com.kbot.entity.KrisBot;
import com.kbot.entity.pcr.CardPool;
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
    private final String MAX_CARD_POOL_PATH = FileUtil.getResourcePath() + "static/files/pcr/cardpool/maxcardpool.json";
    private final String BL_CARD_POOL_PATH = FileUtil.getResourcePath() + "static/files/pcr/cardpool/blcardpool.json";

    @Autowired
    private Bot bot;

    @Bean()
    public CardPool maxCardPool(){
        if(bot instanceof KrisBot){
            return CardPool.builder().comment("maxCardPool").build();
        }else{
            String jsonStr = FileUtil.readJsonFile(MAX_CARD_POOL_PATH);
            return JSON.parseObject(jsonStr, CardPool.class);
        }
    }
    @Bean()
    public CardPool blCardPool(){
        if(bot instanceof KrisBot){
            return CardPool.builder().comment("blCardPool").build();
        }else{
            String jsonStr = FileUtil.readJsonFile(BL_CARD_POOL_PATH);
            return JSON.parseObject(jsonStr, CardPool.class);
        }
    }
}
