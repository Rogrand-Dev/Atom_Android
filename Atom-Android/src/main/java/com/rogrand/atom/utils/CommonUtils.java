package com.rogrand.atom.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建: 陈剑虹 17-5-10.
 */

public class CommonUtils {
    /**
     * 判断手机号格式是否正确（11位，且以1开头）
     */
    public static boolean isMobile(String phone) {
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /***
     * 判断密码是否合法（6-16位数字字母组合）
     */
    public static boolean isPassword(String password) {
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * 是否是合法的身份证号
     */
    public static boolean isIdNumber(String idNumber) {
        Pattern p = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        Matcher m = p.matcher(idNumber);
        return m.matches();
    }
}
