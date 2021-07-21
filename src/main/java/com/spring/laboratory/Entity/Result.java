package com.spring.laboratory.Entity;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

/**
 * http请求最外层数据
 * @param <T>
 */
public class Result<T> {
    //错误码
    private int code;
    //提示信息
    private String msg;
    //具体内容
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
}
