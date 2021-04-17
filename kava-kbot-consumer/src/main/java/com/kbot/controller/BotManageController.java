package com.kbot.controller;

import com.kbot.entity.R;
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
    public R<Object> check(String v) {
        return R.ok(v);
    }

    @GetMapping("/msgTest")
    public R<Object> msgTest() {

        return R.ok(1);
    }

}
