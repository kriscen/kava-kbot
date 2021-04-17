package com.kbot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 文件类操作
 * <p>
 * Created by kris on 2021/4/17
 *
 * @author kris
 */
@Slf4j
public class FileUtil {

    public static String getResourcePath(){
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            log.error("classpath not find",e);
        }
        return path;
    }
}
