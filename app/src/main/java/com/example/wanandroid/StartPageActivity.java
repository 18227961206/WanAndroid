package com.example.wanandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: StartPageActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:29
 * @Description: 启动页
 * @version: 1.1.5
 */

public class StartPageActivity extends Activity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartPageActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
