package com.example.wanandroid.ui.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;

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
