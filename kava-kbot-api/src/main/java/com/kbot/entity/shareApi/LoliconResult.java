package com.kbot.entity.shareApi;

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
public class LoliconResult {
    public Integer code;
    public Integer quota;
    public Integer quota_min_ttl;
    public Integer count;
    public String msg;
    private List<Lolicon> data;
}