package com.rogrand.atom.utils;

import java.security.MessageDigest;

/**
 * MD5 加密工具类
 */
public class MD5Utils {

    // 16进制的字符数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private MD5Utils() {

    }

    private static volatile MD5Utils instance;

    public static MD5Utils getIstance() {
        if (instance == null) {
            synchronized (MD5Utils.class) {
                if (instance == null) {
                    instance = new MD5Utils();
                }
            }
        }
        return instance;
    }

    /**
     * @param source 需要加密的原字符串
     */
    public String MD5Encode(String source) {
        String result = null;
        try {
            result = source;
            // 获得 MD5 摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes("UTF-8"));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 转换字节数组为 16 进制字符串
     *
     * @param bytes 字节数组
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    /**
     * 转换 byte 到 16 进制
     *
     * @param b 要转换的 byte
     * @return 16 进制对应的字符
     */
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


}
