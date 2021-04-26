package com.kbot.entity.shareApi;

import lombok.Data;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: gank 请求对象
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Data
public class GankResult {
    Integer counts;
    List<GankImage> data;
    Integer status;
}
