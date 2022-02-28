package com.kbot.shareApi.entity;

import lombok.Data;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: gank image对象
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Data
public class GankImage {
    String _id;
    String author;
    String category;
    String createdAt;
    String desc;
    List<String> images;
    Integer likeCounts;
    String publishedAt;
    Integer stars;
    String title;
    String type;
    String url;
    Integer views;
}
