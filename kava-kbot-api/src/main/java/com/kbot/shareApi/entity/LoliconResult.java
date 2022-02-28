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
public class LoliconResult {
    public Integer code;
    public Integer count;
    public String msg;
    private List<Lolicon> data;
}
