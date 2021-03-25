package com.kbot.service.impl;

import com.kbot.command.BaseCommand;
import com.kbot.config.BotContainer;
import com.kbot.service.CommandHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 命令处理
 * <p>
 * Created by kris on 2021/3/25
 *
 * @author kris
 */
@Slf4j
@Service
public class CommandHandleServiceImpl implements CommandHandleService {

    @Autowired
    private BotContainer botContainer;

    @Override
    public boolean isCommand(String msg) {
        return botContainer.getCommandHead().stream().anyMatch(head -> msg.startsWith(head));
    }

    @Override
    public BaseCommand getCommand(String msg, Map<String, ? extends BaseCommand> commandContainer) {
        return commandContainer.get(getCommandHead(msg));
    }

    @Override
    public String getContent(String msg) {
        if(!isCommand(msg)){
            return msg;
        }
        String head = getCommandHead(msg);
        return msg.substring(head.length()).trim();
    }


    /**
     * 处理信息，得到头
     * @param msg
     * @return
     */
    private String getCommandHead(String msg){
        Set<String> heads = botContainer.getCommandHead();
        for (String head : heads) {
            if(msg.startsWith(head)){
                return head;
            }
        }
        return null;
    }
}
