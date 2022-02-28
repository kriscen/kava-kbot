package com.kbot.command.everywhere;

import com.google.common.collect.Lists;
import com.kbot.command.CommandProperties;
import com.kbot.service.CommandHandleService;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class DiceCommand implements EverywhereCommand{

    @Autowired
    private CommandHandleService commandHandleService;

    private final String ROLL = "roll";

    @Override
    public CommandProperties properties() {
        StringBuilder sb = new StringBuilder();
        sb.append("投骰子。示例:18 一个18面骰子,d2r18 2个18面骰子");
        return CommandProperties.builder()
                .name("dice")
                .type(1)
                .alias(Lists.newArrayList(ROLL))
                .desc(sb.toString())
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        String content = commandHandleService.getContent(args);
        String result = getDiceResult(content);
        if(StringUtils.isNotBlank(result)){
            return MessageUtils.newChain().plus(result).plus(new At(sender.getId()));
        }else {
            return null;
        }
    }


    private final Pattern SINGLE_PATTERN = Pattern.compile("(\\s)*[\\d+]");
    private final Pattern MULTI_PATTERN = Pattern.compile("(\\s)*(d)[\\d+](r)[\\d+]");

    /**
     * 18    一个18面骰子
     * d1r9  一个9面骰子
     * d2r18 2个18面骰子
     * @param content
     * @return
     */
    private String getDiceResult(String content){
        String d = "d";
        String r = "r";
        String command = content.substring("roll".length()).trim();
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(command)){
            Random random = new Random();
            if(MULTI_PATTERN.matcher(content).find()){
                String s = command.substring(command.indexOf(d)+1, command.indexOf(r));
                int i = Integer.parseInt(s);
                String s2 = command.substring(command.indexOf(r)+1);
                int j = Integer.parseInt(s2);
                List<Integer> list = Lists.newArrayList();
                int count = 0;
                for (int k = 0; k < i; k++) {
                    int rd = random.nextInt(j)+1;
                    list.add(rd);
                    count += rd;
                }
                return sb.append(count).append(list.toString()).toString();
            }else if(SINGLE_PATTERN.matcher(content).find()){
                int i = Integer.parseInt(command);
                int nextInt = random.nextInt(i)+1;
                return nextInt+"";
            }
        }
        return null;
    }
}
