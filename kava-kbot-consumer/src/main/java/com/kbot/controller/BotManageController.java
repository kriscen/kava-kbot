package com.kbot.controller;

import com.kbot.entity.Result;
import com.kbot.event.MessageEvents;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 
 * <p>
 * Created by kris on 2021/3/6
 *
 * @author kris
 */
@RestController
@RequestMapping("/bot")
public class BotManageController {


    @GetMapping("/check")
    public Result<Object> check(String v) {
        return Result.ok(v);
    }

    @GetMapping("/msgTest")
    public Result<Object> msgTest() {

        return Result.ok(1);
    }

}
