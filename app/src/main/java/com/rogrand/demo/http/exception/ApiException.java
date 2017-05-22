package com.rogrand.demo.http.exception;

public class ApiException extends Exception {

    public static final int CODE_NET_EXCEPTION = 1000; // 网络异常
    public static final int CODE_SERVICE_EXCEPTION = 1001; // 服务端返回错误
    public static final int CODE_OTHER_EXCEPTION = 1002; // 其他错误
    public static final String ERROR_MSG_NET_EXCEPTION = "网络不可用";
    public static final String ERROR_MSG_OTHER_EXCEPTION = "请求失败，请稍后重试";

    public ApiException(String msg) {
        super(msg);
    }
}

