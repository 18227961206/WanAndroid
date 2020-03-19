package com.example.wanandroid.ui.me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.MyCollectionAdapter;
import com.example.wanandroid.ui.bean.MyCollectionBean;
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
 * @ClassName: MyCollectionActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:47
 * @Description: 我的收藏
 * @version: 1.1.5
 */

public class MyCollectionActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView title;
    private SmartRefreshLayout refreshLayout;
    private LinearLayout no_data;
    private ListView listView;

    private MyCollectionBean myCollectionBean;
    private MyCollectionAdapter myCollectionAdapter;
    private List<MyCollectionBean.DataBean.DatasBean> dataList;

    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_collection);
        initView();
        MyCollectionInfo(0);
        TheRefresh();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyCollectionActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        no_data = (LinearLayout) findViewById(R.id.no_data);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void MyCollectionInfo(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(MyCollectionActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.loginRequiredOperationGet(RequestURL.listOfFavoriteArticles(page),
                GetCookie.getCookie(MyCollectionActivity.this), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    myCollectionBean = gson.fromJson(json, MyCollectionBean.class);
                    if (myCollectionBean.getErrorCode() == 0) {
                        if (myCollectionBean.getData().getDatas().size() != 0) {
                            refreshLayout.setVisibility(View.VISIBLE);
                            no_data.setVisibility(View.GONE);
                            if (dataList == null || page == 0) {
                                dataList = myCollectionBean.getData().getDatas();
                                myCollectionAdapter = new MyCollectionAdapter(MyCollectionActivity.this, dataList);
                                listView.setDivider(null);
                                listView.setAdapter(myCollectionAdapter);
                            } else {
                                dataList.addAll(myCollectionBean.getData().getDatas());
                                myCollectionAdapter.notifyDataSetChanged();
                            }
                            state = true;
                            initialize = false;
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            refreshLayout.setVisibility(View.GONE);
                            no_data.setVisibility(View.VISIBLE);
                        }
                    } else {
                        state = false;
                        initialize = false;
                        MyToast.myToast(MyCollectionActivity.this, "获取收藏失败,请检查网络是否可用");
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    MyToast.myToast(MyCollectionActivity.this, "获取收藏失败,请检查网络是否可用");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(MyCollectionActivity.this, "获取收藏失败,请检查网络是否可用");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                MyCollectionInfo(0);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(MyCollectionActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(MyCollectionActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (myCollectionBean.getData().getCurPage() < myCollectionBean.getData().getPageCount()) {
                    MyCollectionInfo(myCollectionBean.getData().getCurPage());
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(MyCollectionActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(MyCollectionActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(MyCollectionActivity.this, "我是有底线的");
                }
            }
        });
    }
}
