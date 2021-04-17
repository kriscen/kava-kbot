package com.kbot.command.group.pcr;

import com.google.common.collect.Lists;
import com.kbot.command.friend.FriendCommand;
import com.kbot.dto.pcr.PrincessDto;
import com.kbot.entity.CommandProperties;
import com.kbot.entity.pcr.CardPool;
import com.kbot.service.CommandHandleService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: pcr 扭蛋服务
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Service
public class GachaCommand implements FriendCommand {

    private final String GACHA_SINGLE = "gachaSingle";
    private final String GACHA_TEN = "gachaTen";
    private final String GACHA_WELL = "gachaWell";
    private final List<String> ONE = Lists.newArrayList("单抽");
    private final List<String> TEN = Lists.newArrayList("十连","来一发");
    private final List<String> WELL = Lists.newArrayList("来一井");

    @Autowired
    private CardPool blCardPool;
    @Autowired
    private CommandHandleService commandHandleService;

    @Override
    public CommandProperties properties() {
        List<String> alias = Lists.newArrayList();
        alias.addAll(ONE);
        alias.addAll(TEN);
        alias.addAll(WELL);
        return CommandProperties.builder()
                .name("扭蛋")
                .type(1)
                .alias(alias)
                .build();
    }

    @Override
    public Message execute(User sender, String args, MessageChain messageChain, Contact subject) {
        String mode = getGachaMode(commandHandleService.getContent(args));
        switch(mode){
            case GACHA_SINGLE:
                return gachaSingle(sender);
            case GACHA_TEN:
                log.info("ten");
                break;
            case GACHA_WELL:
                log.info("well");
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 单抽
     * @param sender sender
     * @return message
     */
    private Message gachaSingle(User sender){
        PrincessDto dto = gachaPrincess();
        return MessageUtils.newChain()
                .plus(new PlainText(dto.getName()))
                .plus(new At(sender.getId()));
    }

    private PrincessDto gachaPrincess(){
        Random random = new Random();
        Integer pick = random.nextInt(1000);
        String pickName;
        if(pick <= blCardPool.getUpProb()){
            List<String> up = blCardPool.getUp();
            log.info("pick up...");
            pickName = up.get(random.nextInt(up.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .startNum(3)
                    .build();
        }else if(pick <= blCardPool.getS3Prob()){
            List<String> star3 = blCardPool.getStar3();
            log.info("s3...");
            pickName = star3.get(random.nextInt(star3.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .startNum(3)
                    .build();
        }else if(pick <= blCardPool.getS2Prob()){
            List<String> star2 = blCardPool.getStar2();
            log.info("s2...");
            pickName = star2.get(random.nextInt(star2.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .startNum(2)
                    .build();
        }else {
            List<String> star1 = blCardPool.getStar1();
            log.info("s1...");
            pickName = star1.get(random.nextInt(star1.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .startNum(1)
                    .build();
        }
    }


    /**
     * 根据信息获取是抽卡数量
     * @param message msg
     * @return mode
     */
    private String getGachaMode(String message){
        if(WELL.contains(message)){
            return GACHA_WELL;
        }else if(TEN.contains(message)){
            return GACHA_TEN;
        }else {
            return GACHA_SINGLE;
        }
    }
}
