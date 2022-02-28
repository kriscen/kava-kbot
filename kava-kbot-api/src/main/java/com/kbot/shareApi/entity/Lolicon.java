package com.kbot.shareApi.entity;

import lombok.Data;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: lolicon 对象
 * <p>
 * Created by kris on 2021/4/29
 *
 * @author kris
 */
@Data
public class Lolicon {
    public Long pid;
    public Integer p;
    public Long uid;
    public String title;
    public String author;
    public String url;
    public Boolean r18;
    public Integer width;
    public Integer height;
    public List<String>tags;
}
