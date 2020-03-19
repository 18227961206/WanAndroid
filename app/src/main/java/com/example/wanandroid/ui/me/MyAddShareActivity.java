package com.example.wanandroid.ui.me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.StateBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.GetCookie;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyAddShareActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:47
 * @Description: 我的分享中添加分享
 * @version: 1.1.5
 */

public class MyAddShareActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView share;
    private EditText title;
    private EditText link;
    public static final String action = "MyAddShareActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_add_share);
        initView();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleContent = title.getText().toString();
                String linkContent = link.getText().toString();
                if (!TextUtils.isEmpty(titleContent)) {
                    if (!TextUtils.isEmpty(linkContent)) {
                        MyAddShare(titleContent, linkContent);
                    } else {
                        MyToast.myToast(MyAddShareActivity.this, "文章链接不能为空");
                    }
                } else {
                    MyToast.myToast(MyAddShareActivity.this, "文章标题不能为空");
                }
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        share = (TextView) findViewById(R.id.share);
        title = (EditText) findViewById(R.id.title);
        link = (EditText) findViewById(R.id.link);
    }

    private void MyAddShare(String titleContent, String linkContent) {
        final ProgressDialog progressDialog = ProgressDialog.show(MyAddShareActivity.this, null, "分享中，请稍等...");
        OkHttpRequest.loginRequiredOperationPost(RequestURL.myShareAdd(titleContent, linkContent),
                GetCookie.getCookie(MyAddShareActivity.this), new MyCall() {
                    @Override
                    public void success(String json) {
                        try {
                            Gson gson = new Gson();
                            StateBean stateBean = gson.fromJson(json, StateBean.class);
                            if (stateBean.getErrorCode() == 0) {
                                progressDialog.dismiss();
                                sendBroadcast(new Intent(action));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                MyToast.myToast(MyAddShareActivity.this, stateBean.getErrorMsg());
                            }
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            MyToast.myToast(MyAddShareActivity.this, "分享失败,请检查网络是否可用");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(IOException e) {
                        progressDialog.dismiss();
                        MyToast.myToast(MyAddShareActivity.this, "分享失败,请检查网络是否可用");
                    }
                });
    }
}
