package com.example.wanandroid.ui.me;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.MyIntegralAdapter;
import com.example.wanandroid.ui.bean.MyIntegralBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.GetCookie;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyIntegralActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:48
 * @Description: 我的积分
 * @version: 1.1.5
 */

public class MyIntegralActivity extends AppCompatActivity {

    private RefreshLayout refreshLayout;
    private ImageView goBack;
    private ImageView rules;
    private TextView myIntegral;
    private ListView listView;
    private int integrals = 0;
    private Handler handler;
    private Runnable runnable;
    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;

    private MyIntegralBean myIntegralBean;
    private MyIntegralAdapter myIntegralAdapter;
    private List<MyIntegralBean.DataBean.DatasBean> dataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_integral);
        initView();
        MyIntegral(1);
        TheRefresh();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyIntegralActivity.this, DetailsActivity.class);
                intent.putExtra("title", "本站积分规则");
                intent.putExtra("link", "https://www.wanandroid.com/blog/show/2653");
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        rules = (ImageView) findViewById(R.id.rules);
        myIntegral = (TextView) findViewById(R.id.myIntegral);
        listView = (ListView) findViewById(R.id.listView);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        myIntegral.setText(new SharedPreferencesCookieInfo(MyIntegralActivity.this,
                "cookie").getSharedPreference("coinCount", "").toString());
    }

    private void MyIntegral(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(MyIntegralActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.loginRequiredOperationGet(RequestURL.integralList(page), GetCookie.getCookie(MyIntegralActivity.this), new MyCall() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    myIntegralBean = gson.fromJson(json, MyIntegralBean.class);
                    if (myIntegralBean.getErrorCode() == 0) {
                        if (dataList == null && page == 1) {
                            dataList = myIntegralBean.getData().getDatas();
                            for (MyIntegralBean.DataBean.DatasBean integral : dataList) {
                                integrals += integral.getCoinCount();
                            }
                            myIntegral.setText(integrals + "");
                            myIntegralAdapter = new MyIntegralAdapter(MyIntegralActivity.this, dataList);
                            listView.setAdapter(myIntegralAdapter);
                        } else {
                            assert dataList != null;
                            dataList.addAll(myIntegralBean.getData().getDatas());
                            myIntegralAdapter.notifyDataSetChanged();
                        }
                        state = true;
                        initialize = false;
                        progressDialog.dismiss();
                    } else {
                        state = false;
                        initialize = false;
                        progressDialog.dismiss();
                        MyToast.myToast(MyIntegralActivity.this, "获取积分列表失败,请检查网络");
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    MyToast.myToast(MyIntegralActivity.this, "获取积分列表失败,请检查网络");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(MyIntegralActivity.this, "获取积分列表失败,请检查网络");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                MyIntegral(1);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(MyIntegralActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(MyIntegralActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (myIntegralBean.getData().getCurPage() < myIntegralBean.getData().getPageCount()) {
                    MyIntegral(myIntegralBean.getData().getCurPage() + 1);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(MyIntegralActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(MyIntegralActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(MyIntegralActivity.this, "我是有底线的");
                }
            }
        });
    }
}
