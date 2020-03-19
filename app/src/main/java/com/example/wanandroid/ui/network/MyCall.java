package com.example.wanandroid.ui.network;

import java.io.IOException;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyCall
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:42
 * @Description: 数据请求回掉接口
 * @version: 1.1.5
 */

public interface MyCall {
    void success(String json);
    void failure(IOException e);
}
