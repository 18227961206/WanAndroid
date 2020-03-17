package com.example.wanandroid.ui.me;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.MainActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.LoginBean;
import com.example.wanandroid.ui.network.LoginMyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView title;
    private EditText username;
    private EditText password;
    private Button login;
    private TextView registered;

    private LoginBean loginBean;
    private ProgressDialog progressDialog;
    public static final String action = "LoginActivity";
    private SharedPreferencesCookieInfo sp;
    private SharedPreferencesCookieInfo collection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(username.getText())) {
                    if (!TextUtils.isEmpty(password.getText())) {
                        LoginInfo();
                    } else {
                        MyToast.myToast(LoginActivity.this, "请输入密码");
                    }
                } else {
                    MyToast.myToast(LoginActivity.this, "请输入用户名");
                }

            }
        });

    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        registered = (TextView) findViewById(R.id.registered);
        sp = new SharedPreferencesCookieInfo(LoginActivity.this, "cookie");
        collection = new SharedPreferencesCookieInfo(LoginActivity.this, "collection");
        username.setText(sp.getSharedPreference("username", "").toString());
    }

    private void LoginInfo() {
        progressDialog = ProgressDialog.show(LoginActivity.this, null, "登录中，请稍后...");
        OkHttpRequest.theLogin(RequestURL.login(username.getText().toString(), password.getText().toString()), new LoginMyCall() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void success(String json, Response response, HttpUrl url) {
                try {
                    Gson gson = new Gson();
                    loginBean = gson.fromJson(json, LoginBean.class);
                    if (loginBean.getErrorCode() == 0) {
                        progressDialog.dismiss();
                        // 登录成功后的Response中获取所有的Cookie
                        // 解析cookie
                        Headers loginHeaders = response.headers();
                        List<Cookie> cookie = Cookie.parseAll(url, loginHeaders);
                        StringBuilder cookieStr = new StringBuilder();
                        if (cookie != null) {
                            for (Cookie cookieItem : cookie) {
                                cookieStr.append(cookieItem.name()).append("=").append(cookieItem.value()).append(";");
                            }
                        }

                        // 将cookie和用户信息存储到SharedPreferences中
                        sp.setSharedPreference("cookie", cookieStr.toString());
                        sp.setSharedPreference("username", username.getText().toString());
                        sp.setSharedPreference("password", password.getText().toString());
                        for (int i = 0; i < loginBean.getData().getCollectIds().size(); i++) {
                            collection.setSharedPreference(loginBean.getData().getCollectIds().get(i).toString(),
                                    loginBean.getData().getCollectIds().get(i));
                        }

                        // 发送广播
                        //sendBroadcast(new Intent(action));
                        MyToast.myToast(LoginActivity.this, "登录成功");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        progressDialog.dismiss();
                        MyToast.myToast(LoginActivity.this, loginBean.getErrorMsg());
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                    MyToast.myToast(LoginActivity.this, "登录失败,请检查网络");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                progressDialog.dismiss();
                MyToast.myToast(LoginActivity.this, "登录失败,请检查网络");
            }
        });
    }
}
