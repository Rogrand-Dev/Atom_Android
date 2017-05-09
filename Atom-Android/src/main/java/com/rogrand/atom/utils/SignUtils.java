package com.rogrand.atom.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * API 签名工具类
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
     * @param params 需签名的参数
     * @param secret 加密密钥
     */
    public String sign(Map<String, String> params, String secret) {

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

        // MD5 加密
        return MD5Utils.getIstance().MD5Encode(temp.toString());
    }
}