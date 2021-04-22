package com.kbot.service;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Image;

import java.io.File;
import java.io.InputStream;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 图像处理
 * <p>
 * Created by kris on 2021/3/28
 *
 * @author kris
 */
public interface ImageService {

    /**
     * 根据图片地址得到image
     * @param sender 用于转换图片
     * @param url 网络图片地址
     * @return image
     */
    Image sendImage4Online(Contact sender,String url);

    /**
     * 从本地发送图片
     * @param sender 用于转换图片
     * @param path 本地路径
     * @return image
     */
    Image sendImage4Local(Contact sender, String path);

    /**
     * 从本地发送图片
     * @param sender 用于转换图片
     * @param file 本地路径
     * @return image
     */
    Image sendImage4Local(Contact sender, File file);

    /**
     * 从本地发送图片
     * @param sender 用于转换图片
     * @param is 本地流
     * @return image
     */
    Image sendImage4Local(Contact sender, InputStream is);

    /**
     * 将网络图片下载到本地
     * @param url 网络图片地址
     * @param path 保存在本地的路径
     */
    void downloadImage(String url,String path);

}
