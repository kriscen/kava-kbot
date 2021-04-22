package com.kbot.command.group;

import com.google.common.collect.Lists;
import com.kbot.command.friend.FriendCommand;
import com.kbot.command.group.GroupCommand;
import com.kbot.config.BotContainer;
import com.kbot.constant.FilePathConstant;
import com.kbot.entity.CommandProperties;
import com.kbot.service.ImageService;
import com.kbot.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import java.util.Random;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 猫罗牌
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Service
public class FortuneCommand implements GroupCommand {

    @Autowired
    private ImageService imageService;
    @Autowired
    private BotContainer botContainer;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("fortune")
                .type(1)
                .alias(Lists.newArrayList("抽签","签到"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Map<Long, Integer> fortuneList = botContainer.getFortuneList();
        Integer index = fortuneList.get(sender.getId());
        if(index == null){
            int count = FileUtil.fileCount(FileUtil.getFilePath(FilePathConstant.FORTUNE_IMAGE_MODE));
            index = new Random().nextInt(count);
            fortuneList.put(sender.getId(),index);
        }
        File file = FileUtil.randomFile(FileUtil.getFilePath(FilePathConstant.FORTUNE_IMAGE_MODE), index);
        return MessageUtils.newChain()
                .plus(imageService.sendImage4Local(subject,file))
                .plus(new At(sender.getId()));
    }

}
