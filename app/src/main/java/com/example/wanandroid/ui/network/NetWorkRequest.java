package com.example.wanandroid.ui.network;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.wanandroid.ui.application.MyApplication;
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
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class NetWorkRequest {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    static void request(final Request request, final MyCall mycall) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycall.failure(e);
                    }
                });
            }

            @SuppressLint("ApplySharedPref")
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (TextUtils.isEmpty(str)) {
                            MyToast.myToast(MyApplication.getContext(), "请求数据为空");
                        } else {
                            mycall.success(str);
                        }
                    }
                });
            }
        });
    }
}
