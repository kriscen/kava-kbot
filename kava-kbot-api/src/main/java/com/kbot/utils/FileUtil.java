package com.kbot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

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

    public static String getFilePath(String filePath){
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        String path = null;
        try {
            path = classPathResource.getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 读取json文件
     * @param fileName path+fileName
     * @return json
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从文件夹中随机一个文件
     * @param filePath 文件夹地址
     * @param Index 文件夹中第几个文件
     * @return file
     */
    public static File randomFile(String filePath,int Index) {
        File folder = new File(filePath);
        if(!folder.exists()){
            return null;
        }
        File[] files = folder.listFiles();
        return files[Index];
    }

    /**
     * 文件夹中文件数量
     * @param filePath 文件夹地址
     * @return num
     */
    public static int fileCount(String filePath) {
        File folder = new File(filePath);
        if(!folder.exists()){
            return 0;
        }
        return folder.listFiles().length;
    }


}
