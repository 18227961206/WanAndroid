package com.example.wanandroid.ui.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.MyShareAdapter;
import com.example.wanandroid.ui.bean.MyShareBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.GetCookie;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyShareActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:48
 * @Description: 我的分享
 * @version: 1.1.5
 */

public class MyShareActivity extends AppCompatActivity {

    private ImageView goBack;
    private ImageView add;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private LinearLayout noData;

    private MyShareBean myShareBean;
    private MyShareAdapter myShareAdapter;
    private List<MyShareBean.DataBean.ShareArticlesBean.DatasBean> dataList;

    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_share);
        initView();
        MyShare(1);
        TheRefresh();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyShareActivity.this, MyAddShareActivity.class));
            }
        });

        MyShareActivity.this.registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                MyShare(1);
            }
        }, new IntentFilter(MyAddShareActivity.action));

        MyShareActivity.this.registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                MyShareAdapter.theRefresh();
                myShareAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyShareActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        add = (ImageView) findViewById(R.id.add);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        noData = (LinearLayout) findViewById(R.id.no_data);
    }

    private void MyShare(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(MyShareActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.loginRequiredOperationGet(RequestURL.myShareList(page), GetCookie.getCookie(MyShareActivity.this), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    myShareBean = gson.fromJson(json, MyShareBean.class);
                    if (myShareBean.getErrorCode() == 0) {
                        if (myShareBean.getData().getShareArticles().getDatas().size() != 0) {
                            refreshLayout.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            if (dataList == null || page == 1) {
                                dataList = myShareBean.getData().getShareArticles().getDatas();
                                myShareAdapter = new MyShareAdapter(MyShareActivity.this, dataList);
                                listView.setDivider(null);
                                listView.setAdapter(myShareAdapter);
                            } else {
                                dataList.addAll(myShareBean.getData().getShareArticles().getDatas());
                                myShareAdapter.notifyDataSetChanged();
                            }
                            state = true;
                            initialize = false;
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            refreshLayout.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                        }
                    } else {
                        state = false;
                        initialize = false;
                        MyToast.myToast(MyShareActivity.this, "获取收藏失败,请检查网络是否可用");
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    MyToast.myToast(MyShareActivity.this, "获取收藏失败,请检查网络是否可用");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(MyShareActivity.this, "获取分享失败,请检查网络是否可用");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                MyShare(1);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(MyShareActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(MyShareActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (myShareBean.getData().getShareArticles().getCurPage() < myShareBean.getData().getShareArticles().getPageCount()) {
                    MyShare(myShareBean.getData().getShareArticles().getCurPage() + 1);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(MyShareActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(MyShareActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(MyShareActivity.this, "我是有底线的");
                }
            }
        });
    }
}
