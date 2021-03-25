package com.kbot.sys.exception;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 自定义业务异常 api专用
 * <p>
 * Created by kris on 2021/3/16
 *
 * @author kris
 */
public class KbotApiException extends Exception {
    public KbotApiException(String msg) {
        super(msg);
    }
}
