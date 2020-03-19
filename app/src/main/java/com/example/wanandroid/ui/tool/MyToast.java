package com.example.wanandroid.ui.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyToast
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:33
 * @Description: 全局Toast
 * @version: 1.1.5
 */

public class MyToast {
    public static void myToast(Context context, String content) {
        Toast toast = new Toast(context);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
        TextView contentView = (TextView) view.findViewById(R.id.content);
        contentView.setText(content);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
