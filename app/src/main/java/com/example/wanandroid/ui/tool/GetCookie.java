package com.example.wanandroid.ui.tool;

import android.content.Context;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: GetCookie
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:32
 * @Description: 获取Cookie
 * @version: 1.1.5
 */

public class GetCookie {
    public static String getCookie(Context context){
        SharedPreferencesCookieInfo sharedPreferencesCookieInfo = new SharedPreferencesCookieInfo(context, "cookie");
        return sharedPreferencesCookieInfo.getSharedPreference("cookie","").toString();
    }
}
