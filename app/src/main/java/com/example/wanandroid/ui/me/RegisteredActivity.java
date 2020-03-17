package com.example.wanandroid.ui.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.RegisteredBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;

import java.io.IOException;

public class RegisteredActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView title;
    private EditText username;
    private EditText password;
    private EditText repassword;
    private TextView login;
    private Button registered;
    private SharedPreferencesCookieInfo sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registered);
        initView();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
                finish();
            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(username.getText())) {
                    if (!TextUtils.isEmpty(password.getText())) {
                        if (!TextUtils.isEmpty(repassword.getText())) {
                            if (TextUtils.isEmpty(password.getText()) == TextUtils.isEmpty(repassword.getText())) {
                                final ProgressDialog progressDialog = ProgressDialog.show(RegisteredActivity.this, null, "注册中，请稍等...");
                                OkHttpRequest.post(RequestURL.registered(username.getText().toString(), password.getText().toString(), repassword.getText().toString()), new MyCall() {
                                    @Override
                                    public void success(String json) {
                                        try {
                                            Gson gson = new Gson();
                                            RegisteredBean registeredBean = gson.fromJson(json, RegisteredBean.class);
                                            if (registeredBean.getErrorCode() == 0) {
                                                progressDialog.dismiss();
                                                sp.setSharedPreference("username", username.getText().toString());
                                                sp.setSharedPreference("password", password.getText().toString());
                                                MyToast.myToast(RegisteredActivity.this, "注册成功,请登录");
                                                startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
                                                finish();
                                            } else {
                                                progressDialog.dismiss();
                                                MyToast.myToast(RegisteredActivity.this, registeredBean.getErrorMsg());
                                            }
                                        } catch (Exception e) {
                                            progressDialog.dismiss();
                                            MyToast.myToast(RegisteredActivity.this, "注册失败,请检查网络是否可用");
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void failure(IOException e) {
                                        MyToast.myToast(RegisteredActivity.this, "注册失败,请检查网络是否可用");
                                    }
                                });
                            } else {
                                MyToast.myToast(RegisteredActivity.this, "两次密码不相同");
                            }
                        } else {
                            MyToast.myToast(RegisteredActivity.this, "请再次输入密码");
                        }
                    } else {
                        MyToast.myToast(RegisteredActivity.this, "请输入密码");
                    }
                } else {
                    MyToast.myToast(RegisteredActivity.this, "请输入用户名");
                }
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        registered = (Button) findViewById(R.id.registered);
        login = (TextView) findViewById(R.id.login);
        sp = new SharedPreferencesCookieInfo(RegisteredActivity.this, "cookie");
    }
}
