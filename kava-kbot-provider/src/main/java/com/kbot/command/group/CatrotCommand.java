package com.kbot.command.group;

import com.google.common.collect.Lists;
import com.kbot.config.BotContainer;
import com.kbot.constant.FilePathConstant;
import com.kbot.entity.CommandProperties;
import com.kbot.entity.TarotInfo;
import com.kbot.service.ImageService;
import com.kbot.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class CatrotCommand implements GroupCommand {

    @Autowired
    private ImageService imageService;
    @Autowired
    private BotContainer botContainer;

    @Override
    public CommandProperties properties() {
        return CommandProperties.builder()
                .name("catrot")
                .type(1)
                .desc("随机一张猫罗牌")
                .alias(Lists.newArrayList("来一张猫罗牌","猫罗牌"))
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        Map<Long, TarotInfo> catrotList = botContainer.getCatrotList();
        TarotInfo info = catrotList.get(sender.getId());
        if(info == null){
            List<TarotInfo> infos = botContainer.getCatrotTextList();
            info = infos.get(new Random().nextInt(infos.size()));
            catrotList.put(sender.getId(),info);
        }
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("[")
                .append(info.getName())
                .append(" ")
                .append(info.isStatus() ? "正位" : "逆位")
                .append("]").append("\n")
                .append(info.isStatus() ? info.getNormalDes() : info.getSeDlamron());
        Image image = imageService.sendImage4Local(subject, FileUtil.getFilePath(FilePathConstant.CATROT_IMAGE_MODE + "/" + info.getImgName()));
        return MessageUtils.newChain()
                .plus(image).plus("\n")
                .plus(resultStr.toString())
                .plus(new At(sender.getId()));
    }

}
