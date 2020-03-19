package com.example.wanandroid.ui.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyApplication
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:51
 * @Description: 全局Application
 * @version: 1.1.5
 */

@SuppressLint("Registered")
public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}