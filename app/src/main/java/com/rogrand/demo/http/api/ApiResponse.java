package com.rogrand.demo.http.api;

public class ApiResponse<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("error_code=" + code + " msg=" + msg);
        if (null != data) {
            sb.append(" result:" + data.toString());
        }
        return sb.toString();
    }
}