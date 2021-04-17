package com.kbot.command.friend;


import com.kbot.command.group.GroupCommand;
import com.kbot.entity.CommandProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Program Name:
 * <p>
 * Description: 每日人品
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Service
public class RpCommand implements FriendCommand {
    public static Map<Long, Integer> MAP_RP = new HashMap<>();

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("rp")
                .type(1)
                .alias(Arrays.asList("人品","十连"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Member member = (Member)sender;
        //获取群员Q号
        Long groupUserId = member.getId();
        String groupUserName = member.getNameCard();
        //rp
        int rollNum = 0;
        if (MAP_RP.containsKey(groupUserId)) {
            rollNum = MAP_RP.get(groupUserId);
        } else {
            rollNum = 100;
            MAP_RP.put(groupUserId, rollNum);
        }

        //可以随机点装饰性语句
        String msgEx = getMsgEx(rollNum);
        String resultStr = String.format("【%s】今天的人品值：%s%s", groupUserName, rollNum, msgEx);
        return new PlainText(resultStr);
    }


    private String getMsgEx(int rollNum) {
        if (rollNum <= 0) {
            return "这人可真是惨到家了...";
        } else if (rollNum == 9) {
            return "baka~";
        } else if (rollNum == 100) {
            return "恭喜恭喜";
        }
        return "";
    }
}
