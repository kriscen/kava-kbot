package com.kbot.runner;


import com.kbot.config.BotContainer;
import com.kbot.bot.constant.FilePathConstant;
import com.kbot.lottery.entity.TarotInfo;
import com.kbot.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

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

    @Autowired
    private BotContainer botContainer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        catrotLoad();
    }

    /**
     * 加载catrot文本
     */
    private void catrotLoad(){
        List<TarotInfo> list = botContainer.getCatrotTextList();
        File tarotFile = new File(FileUtil.getFilePath(FilePathConstant.CATROT_FILE_MODE));
        //创建读取器
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(tarotFile));
        } catch (FileNotFoundException e) {
            log.error("catrot text can not find");
            return;
        }

        //逐行读取文件
        String freeTimeStr = null;
        //跳过第一行 是标识的数据来源
        try {
            reader.readLine();
            while ((freeTimeStr = reader.readLine()) != null) {
                //过滤掉空行
                if (freeTimeStr.length() <= 0){
                    continue;
                }
                TarotInfo positiveInfo = new TarotInfo();
                TarotInfo negativeInfo = new TarotInfo();
                //首先是图片名称
                positiveInfo.setImgName(freeTimeStr);
                negativeInfo.setImgName(freeTimeStr);
                //再往下读一行，是卡牌名称
                freeTimeStr = reader.readLine();
                positiveInfo.setName(freeTimeStr);
                negativeInfo.setName(freeTimeStr);
                //再往下读一行，是正位描述
                freeTimeStr = reader.readLine();
                freeTimeStr = freeTimeStr.substring(freeTimeStr.indexOf("：") + 1);
                positiveInfo.setNormalDes(freeTimeStr);
                positiveInfo.setStatus(true);
                //再往下读一行，是逆位描述
                freeTimeStr = reader.readLine();
                freeTimeStr = freeTimeStr.substring(freeTimeStr.indexOf("：") + 1);
                negativeInfo.setSeDlamron(freeTimeStr);
                negativeInfo.setStatus(false);
                list.add(positiveInfo);
                list.add(negativeInfo);
            }
        }catch (Exception e){

        }finally {
            //关闭读取器
            try {
                reader.close();
            } catch (IOException e) {

            }
        }
        log.info("catrot text init success");
    }
}
