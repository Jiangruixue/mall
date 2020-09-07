package com.hynet.mall.utils;

import com.hynet.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * @progrm:
 * @description: md5
 * @auther:
 * @create:
 */
public class MD5Utils {

    public static String md5(String originString) throws NoSuchAlgorithmException {
        MessageDigest md5 =  MessageDigest.getInstance("MD5");
        byte[] result = md5.digest((originString + Constant.SATL).getBytes());
        return Base64.encodeBase64String(result);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("1234----"+ MD5Utils.md5("1234"));
    }
}
