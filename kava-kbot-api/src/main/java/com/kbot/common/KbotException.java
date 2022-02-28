package com.kbot.common;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 自定义业务异常
 * <p>
 * Created by kris on 2021/3/16
 *
 * @author kris
 */
public class KbotException extends Exception {
    public KbotException(String msg) {
        super(msg);
    }
}
