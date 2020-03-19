package com.example.wanandroid.ui.system;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.bean.PublicBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.officialAccounts.OfficialAccountsInfoActivity;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
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
 * @ClassName: SystemOneInfoActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:37
 * @Description: 体系详情
 * @version: 1.1.5
 */

public class SystemOneInfoActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView titleView;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private String title;
    private int id;
    private PublicBean publicBean;
    private PublicAdapter publicAdapter;
    private List<PublicBean.DataBean.DatasBean> dataList;
    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_system_one_info);
        initView();
        Intent getItemInfo = getIntent();
        title = getItemInfo.getStringExtra("title");
        id = getItemInfo.getIntExtra("id", 0);
        titleView.setText(title);
        SystemOneInfo(id, 0);
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
                Intent intent = new Intent(SystemOneInfoActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });

        // 接收广播,刷新数据
        SystemOneInfoActivity.this.registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                publicAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        titleView = (TextView) findViewById(R.id.title);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void SystemOneInfo(int id, final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(SystemOneInfoActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.get(RequestURL.systemOneInfoPage(id, page), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    publicBean = gson.fromJson(json, PublicBean.class);
                    if (publicBean.getErrorCode() == 0) {
                        if (dataList == null || page == 0) {
                            dataList = publicBean.getData().getDatas();
                            publicAdapter = new PublicAdapter(SystemOneInfoActivity.this, dataList, "SystemOneInfoActivity");
                            listView.setDivider(null);
                            listView.setAdapter(publicAdapter);
                        } else {
                            dataList.addAll(publicBean.getData().getDatas());
                            publicAdapter.notifyDataSetChanged();
                        }
                        state = true;
                        initialize = false;
                        progressDialog.dismiss();
                    } else {
                        state = false;
                        initialize = false;
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(SystemOneInfoActivity.this, "请求失败,请检查网络");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                SystemOneInfo(id, 1);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(SystemOneInfoActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(SystemOneInfoActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    SystemOneInfo(id, publicBean.getData().getCurPage());
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(SystemOneInfoActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(SystemOneInfoActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(SystemOneInfoActivity.this, "我是有底线的");
                }
            }
        });
    }
}
