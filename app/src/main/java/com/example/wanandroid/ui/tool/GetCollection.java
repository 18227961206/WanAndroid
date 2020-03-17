package com.example.wanandroid.ui.tool;

import android.content.Context;

public class GetCollection {
    public static String getCookie(Context context){
        SharedPreferencesCookieInfo sharedPreferencesCookieInfo = new SharedPreferencesCookieInfo(context, "collection");
        return sharedPreferencesCookieInfo.getSharedPreference("collection","").toString();
    }
}
