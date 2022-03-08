package com.kbot.lottery.entity.tarot;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kbot.common.BaseEntity;
import com.kbot.lottery.constant.TarotStatus;
import com.kbot.lottery.constant.TarotType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Program Name:
 * <p>
 * Description: 塔罗牌
 * <p>
 * Created by kris on 2022/3/8
 *
 * @author kris
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lottery_tarot")
public class Tarot extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;
    /**
     * 对应图片
     */
    private String imgName;
    /**
     * 描述
     */
    private String desc;
    /**
     * 1:正位  2:逆位
     * {@link TarotStatus}
     */
    private String status;
    /**
     * 1:塔罗牌 2:猫罗牌
     * {@link TarotType}
     */
    private String type;
}
