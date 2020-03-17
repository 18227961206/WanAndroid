package com.example.wanandroid.ui.tool;

import android.content.Context;

public class GetCookie {
    public static String getCookie(Context context){
        SharedPreferencesCookieInfo sharedPreferencesCookieInfo = new SharedPreferencesCookieInfo(context, "cookie");
        return sharedPreferencesCookieInfo.getSharedPreference("cookie","").toString();
    }
}
