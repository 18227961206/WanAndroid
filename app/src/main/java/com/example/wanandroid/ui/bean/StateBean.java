package com.example.wanandroid.ui.bean;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: StateBean
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:55
 * @Description: 全局状态数据
 * @version: 1.1.5
 */

public class StateBean {

    /**
     * data : null
     * errorCode : 0
     * errorMsg :
     */

    private Object data;
    private int errorCode;
    private String errorMsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
