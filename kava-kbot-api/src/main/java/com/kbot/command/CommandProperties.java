package com.kbot.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 指令属性
 * <p>
 * Created by kris on 2021/3/23
 *
 * @author kris
 */
@Data
@Builder
public class CommandProperties {
    /**
     * 指令名称
     */
    private String name;

    /**
     * 匹配类别
     */
    private Integer type;

    /**
     * 指令别称
     */
    private List<String> alias;

    /**
     * 指令说明
     */
    private String desc;
}
