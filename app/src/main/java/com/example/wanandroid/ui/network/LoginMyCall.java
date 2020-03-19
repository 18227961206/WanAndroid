package com.example.wanandroid.ui.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: LoginMyCall
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:42
 * @Description: 登录回掉接口
 * @version: 1.1.5
 */

public interface LoginMyCall {
    void success(String json, Response response, HttpUrl url);
    void failure(IOException e);
}
