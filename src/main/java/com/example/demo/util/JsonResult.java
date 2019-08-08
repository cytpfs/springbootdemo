package com.example.demo.util;

public class JsonResult<T> {
    private String code;
    private T data;
    private String msg;

    public JsonResult(T data) {
        this.data = data;
        this.code = "0";
        this.msg = "成功";
    }

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
