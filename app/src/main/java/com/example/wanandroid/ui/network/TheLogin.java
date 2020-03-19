package com.example.wanandroid.ui.network;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.wanandroid.ui.application.MyApplication;
import com.example.wanandroid.ui.me.LoginActivity;
import com.example.wanandroid.ui.tool.MyToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: TheLogin
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:46
 * @Description: 登录请求
 * @version: 1.1.5
 */

public class TheLogin {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                //自定义
                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<HttpUrl, List<Cookie>>();

                //复写
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url, cookies);
                }

                //复写
                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url);
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    static void theLogin(final Request request, final LoginMyCall loginMyCall) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginMyCall.failure(e);
                    }
                });
            }

            @SuppressLint("ApplySharedPref")
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(str)) {
                            MyToast.myToast(MyApplication.getContext(), "请求数据为空");
                        } else {
                            loginMyCall.success(str,response,request.url());
                        }
                    }
                });
            }
        });
    }
}
