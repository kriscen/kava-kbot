package com.kbot.constant;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: 模块文件路径  TODO 后序改进
 * <p>
 * Created by kris on 2021/4/18
 *
 * @author kris
 */
public class FilePathConstant {
    /*  ------ file ------  */
    /**
     * PCR卡池文件夹
     */
    public final static String PCR_FILE_CARDPOOL_MODE = "static/files/pcr/cardpool";
    /**
     * pcr卡池
     */
    public final static String MAX_CARD_POOL_PATH = PCR_FILE_CARDPOOL_MODE + "/maxcardpool.json";
    /**
     * bcr卡池
     */
    public final static String BL_CARD_POOL_PATH = PCR_FILE_CARDPOOL_MODE + "/blcardpool.json";
    /**
     * 猫罗牌文案
     */
    public final static String CATROT_FILE_MODE = "static/files/catrot/catrot";
    /**
     * setu文件夹
     */
    public final static String SETU_FILE_MODE = "static/files/setu";
    /**
     * mzitu url文件
     */
    public final static String MZITU_FILE_MODE = SETU_FILE_MODE+ "/mzitu";


    /*  ------ image ------  */
    public final static String PCR_IMAGE_MODE = "static/image/pcr";
    /**
     * 十连小头像
     */
    public final static String PCR_AVATAR_TINY_IMAGE_MODE = PCR_IMAGE_MODE + "/avatar/tiny";
    /**
     * 单抽小头像
     */
    public final static String PCR_AVATAR_SMALL_IMAGE_MODE = PCR_IMAGE_MODE + "/avatar/small";
    /**
     * 未用
     */
    public final static String PCR_AVATAR_MEDIUM_IMAGE_MODE = PCR_IMAGE_MODE + "/avatar/medium";
    /**
     * 猫罗牌模块地址
     */
    public final static String CATROT_IMAGE_MODE = "static/image/catrot";
    /**
     * 每日卜卦模块地址
     */
    public final static String FORTUNE_IMAGE_MODE = "static/image/fortune";



}
