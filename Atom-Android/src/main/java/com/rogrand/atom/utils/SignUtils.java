package com.rogrand.atom.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * 签名工具类
 */
public class SignUtils {

    private SignUtils() {

    }

    private static volatile SignUtils instance;

    public static SignUtils getInstance() {
        if (instance == null) {
            synchronized (SignUtils.class) {
                if (instance == null) {
                    instance = new SignUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 签名
     *
     * @param params    需签名的参数
     * @param secret    加密密钥
     * @param algorithm 加密方式
     * @return 签名结果
     */
    public String sign(Map<String, String> params, String secret, String algorithm) {

        // 参数名排序
        String[] keyArray = params.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

        // 拼接参数
        StringBuilder temp = new StringBuilder();
        for (String key : keyArray) {
            temp.append(key);

            // 防止中文的参数值出现乱码，先转换为 UTF-8 编码
            Object value = params.get(key);
            if (null != value) {
                try {
                    String valueString = String.valueOf(value);
                    temp.append(URLEncoder.encode(valueString, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }
        temp.append(secret);

        String returnValue = temp.toString();

        if (algorithm.equals("MD5")) {  // MD5 加密
            returnValue = EncryptUtils.encryptMD5ToString(returnValue);
        } else if (algorithm.equals("SHA1")) {  // SHA1 加密
            returnValue = EncryptUtils.encryptSHA1ToString(returnValue);
        }
        return returnValue;
    }

}