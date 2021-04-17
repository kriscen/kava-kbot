package com.kbot.service;

import net.mamoe.mirai.message.data.Image;

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
     * 发送图片
     * @return image
     */
    Image sendImage();
}
