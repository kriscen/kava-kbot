package com.kbot.command.group.pcr;

import com.google.common.collect.Lists;
import com.kbot.command.group.GroupCommand;
import com.kbot.constant.pcr.PrincessStar;
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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
public class GachaCommand implements GroupCommand {

    private final String GACHA_SINGLE = "gachaSingle";
    private final String GACHA_TEN = "gachaTen";
    private final String GACHA_NESS = "gachaNess";
    private final String GACHA_WELL = "gachaWell";
    private final List<String> ONE = Lists.newArrayList("单抽");
    private final List<String> NESS = Lists.newArrayList("必得");
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
        alias.addAll(NESS);
        alias.addAll(WELL);
        return CommandProperties.builder()
                .name("gacha")
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
                return gachaTen(sender,GACHA_TEN);
            case GACHA_NESS:
                return gachaTen(sender,GACHA_NESS);
            case GACHA_WELL:
                return gachaWell(sender);
            default:
                break;
        }
        return MessageUtils.newChain()
                .plus(new PlainText("扭蛋机坏了┭┮﹏┭┮"))
                .plus(new At(sender.getId()));
    }

    /**
     * 单抽
     * @param sender sender
     * @return message
     */
    private Message gachaSingle(User sender){
        PrincessDto dto = gachaPrincess();
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getName());
        sb.append("(");
        for (int j = 0; j < dto.getPrincessStar().getStarNUm(); j++) {
            sb.append("✩");
        }
        sb.append(")");
        return MessageUtils.newChain()
                .plus(new PlainText(sb))
                .plus(new At(sender.getId()));
    }

    /**
     * 十连
     * @param sender sender
     * @param mode mode
     * @return message
     */
    private Message gachaTen(User sender,String mode){
        List<PrincessDto> ten;
        if(GACHA_TEN.equals(mode)){
            ten = gachaTenPrincess();
        }else {
            ten = gachaTenPrincessNess();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ten.size(); i++) {
            sb.append(ten.get(i).getName());
            sb.append("(");
            for (int j = 0; j < ten.get(i).getPrincessStar().getStarNUm(); j++) {
                sb.append("✩");
            }
            sb.append(")");
            if(i != ten.size() -1){
                sb.append(",");
            }else {
                sb.append("。");
            }
        }
        return MessageUtils.newChain().plus(sb.toString()).plus(new At(sender.getId()));
    }

    /**
     * 一井
     * @param sender sender
     * @return message
     */
    private Message gachaWell(User sender){
        List<PrincessDto> well = Lists.newArrayList();
        //300抽，30次十连
        int wellNum = 30;
        for (int i = 0; i < wellNum; i++) {
            well.addAll(gachaTenPrincess());
        }
        int fistUp = 0;
        List<PrincessDto> upResult = Lists.newArrayList();
        List<PrincessDto> star3Result = Lists.newArrayList();
        List<PrincessDto> star2Result = Lists.newArrayList();
        List<PrincessDto> star1Result = Lists.newArrayList();
        for (int i = 0; i < well.size(); i++) {
            if(fistUp == 0 && PrincessStar.STAR_UP.equals(well.get(i).getPrincessStar())){
                fistUp = i+1;
            }
            switch(well.get(i).getPrincessStar()){
                case STAR_UP:
                    upResult.add(well.get(i));
                    break;
                case STAR_3:
                    star3Result.add(well.get(i));
                    break;
                case STAR_2:
                    star2Result.add(well.get(i));
                    break;
                case STAR_1:
                    star1Result.add(well.get(i));
                    break;
                default:
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(upResult.isEmpty()){
            sb.append("没有抽到up角色。").append("\n");
        }else{
            sb.append(String.format("在第%d抽抽到up角色。",fistUp)).append("\n");
            formatStar3(upResult, sb);
            if (star3Result.isEmpty()){
                sb.append("连三星都没有抽到。");
            }else{
                formatStar3(star3Result, sb);
            }
        }

        sb.deleteCharAt(sb.length()-1).append("。");
        return MessageUtils.newChain().plus(sb.toString()).plus(new At(sender.getId()));
    }

    private void formatStar3(List<PrincessDto> star3Result, StringBuilder sb) {
        Map<String, Long> star3Count = star3Result.stream().collect(Collectors.groupingBy(PrincessDto::getName, Collectors.counting()));
        Set<String> set = star3Count.keySet();
        set.forEach(t->{
            sb.append(t).append(" * ").append(star3Count.get(t)).append(",");
        });
    }

    /**
     * 十连
     * @return 十连
     */
    private List<PrincessDto> gachaTenPrincess(){
        List<PrincessDto> ten = Lists.newArrayList();
        int normal = 9;
        for (int i = 1; i <= normal; i++) {
            ten.add(gachaPrincess());
        }
        long count = ten.stream().filter(t -> PrincessStar.STAR_1.equals(t.getPrincessStar())).count();
        if(count == 9){
            List<String> star2 = blCardPool.getStar2();
            String pickName = star2.get(new Random().nextInt(star2.size()));
            ten.add(PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_2)
                    .build());
        }else{
            ten.add(gachaPrincess());
        }
        return ten;
    }
    /**
     * 必得
     * @return 十连
     */
    private List<PrincessDto> gachaTenPrincessNess(){
        List<PrincessDto> ten = Lists.newArrayList();
        int normal = 9;
        for (int i = 1; i <= normal; i++) {
            ten.add(gachaPrincess());
        }
        boolean indemnify = ten.stream().anyMatch(t -> t.getPrincessStar().getStarNUm() == 3);
        if(!indemnify){
            ten.add(indemnifyPrincessNess());
        }else{
            ten.add(gachaPrincess());
        }
        return ten;
    }

    /**
     * 必得保底
     * @return dto
     */
    private PrincessDto indemnifyPrincessNess(){
        int total = blCardPool.getUpProb()+blCardPool.getS3Prob();
        Random random = new Random();
        int pick = random.nextInt(total);
        String pickName;
        if(pick <= blCardPool.getUpProb()){
            List<String> up = blCardPool.getUp();
            pickName = up.get(random.nextInt(up.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_UP)
                    .build();
        }else{
            List<String> star3 = blCardPool.getStar3();
            pickName = star3.get(random.nextInt(star3.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_3)
                    .build();
        }
    }
    /**
     * 单抽
     * @return dto
     */
    private PrincessDto gachaPrincess(){
        Random random = new Random();
        int pick = random.nextInt(1000);
        String pickName;
        if(pick <= blCardPool.getUpProb()){
            List<String> up = blCardPool.getUp();
            pickName = up.get(random.nextInt(up.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_UP)
                    .build();
        }else if(pick <= blCardPool.getS3Prob()){
            List<String> star3 = blCardPool.getStar3();
            pickName = star3.get(random.nextInt(star3.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_3)
                    .build();
        }else if(pick <= blCardPool.getS2Prob()){
            List<String> star2 = blCardPool.getStar2();
            pickName = star2.get(random.nextInt(star2.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_2)
                    .build();
        }else {
            List<String> star1 = blCardPool.getStar1();
            pickName = star1.get(random.nextInt(star1.size()));
            return PrincessDto.builder()
                    .name(pickName)
                    .avatarPath(null)
                    .princessStar(PrincessStar.STAR_1)
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
        }else if(NESS.contains(message)){
            return GACHA_NESS;
        }else {
            return GACHA_SINGLE;
        }
    }
}