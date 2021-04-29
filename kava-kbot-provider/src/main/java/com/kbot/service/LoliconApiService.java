package com.kbot.service;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: lolocon单独处理
 * <p>
 * Created by kris on 2021/4/29
 *
 * @author kris
 */
public interface LoliconApiService {

    /**
     * 从lolicon查询图片
     * @param r18 0为非 R18，1为 R18，2为混合
     * @param searchWord 若指定关键字，将会返回从插画标题、作者、标签中模糊搜索的结果
     * @return 图片url
     */
    String getLoliconImage(int r18,String searchWord);

}
