package com.kbot.entity.shareApi;

import lombok.Data;

import java.util.List;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: pcr 活动日历
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Data
public class BcrCalendarResult {
    List<BcrCalendar> data;
}
