package com.kbot.entity.spider;

import com.kbot.constant.ShareApiConstant;
import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: mzitu spider 抽取数据实体
 * <p>
 * Created by kris on 2021/4/24
 *
 * @author kris
 */
@Data
public class MzituRepo {
    String imageUrl;
}
