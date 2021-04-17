package com.kbot.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: context
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;
 
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @return context
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name beanName
     * @return bean
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }


    /**
     * 通过class获取Bean.
     * @param clazz clazz
     * @param <T> T
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name name
     * @param clazz clazz
     * @param <T> T
     * @return bean
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
 
}