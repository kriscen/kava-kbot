package com.kbot.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Program Name: kava-kbot
 * <p>
 * Description: md5
 * <p>
 * Created by kris on 2021/3/22
 *
 * @author kris
 */
public class MD5Util {

    public static String createID(String key){
        key = String.valueOf(System.currentTimeMillis()) + key;
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 16位的加密
        return buf.toString().substring(8, 24); 
        //         32位的加密
        //return buf.toString());// 

    }

    public static void main(String[] args) throws Exception {
        System.out.println(MD5Util.createID("kbot"));
    }

}