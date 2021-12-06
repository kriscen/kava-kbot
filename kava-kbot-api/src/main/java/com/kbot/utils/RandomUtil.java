package com.kbot.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * Program Name: 
 * <p>
 * Description: 随机一些东西
 * <p>
 * Created by kris on 2021/12/6
 *
 * @author kris
 */
public class RandomUtil {

    public static String randomString(List<String> factors){
        if(CollectionUtils.isEmpty(factors)){
            return "";
        }
        int result = new Random().nextInt(factors.size());
        return factors.get(result);
    }
}
