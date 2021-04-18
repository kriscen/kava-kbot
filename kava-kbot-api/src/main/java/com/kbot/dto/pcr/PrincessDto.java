package com.kbot.dto.pcr;

import com.kbot.constant.pcr.PrincessStar;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 抽卡时单个角色
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
@Slf4j
@Data
@Builder
public class PrincessDto {
    /**
     * 公主名
     */
    private String name;
    /**
     * 公主头像地址
     */
    private String avatarPath;
    /**
     * 星数
     */
    private PrincessStar princessStar;
}
