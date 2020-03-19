package com.example.wanandroid.ui.project;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.bean.PublicBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: ProjectInfoActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:41
 * @Description: 项目详情
 * @version: 1.1.5
 */

public class ProjectInfoActivity extends AppCompatActivity {

    private String title;
    private int id;
    private ImageView goBack;
    private TextView textTitle;
    private ListView listView;
    private PublicBean publicBean;
    private PublicAdapter publicAdapter;
    private List<PublicBean.DataBean.DatasBean> dataList;
    private Boolean state = false;
    private RefreshLayout refreshLayout;
    private ProgressDialog progressDialog;
    private Boolean Initialize = true;
    private Handler handler;
    private Runnable runnable;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_project_info);
        initView();
        Intent getItemInfo = getIntent();
        title = getItemInfo.getStringExtra("title");
        id = getItemInfo.getIntExtra("id", 0);
        textTitle.setText(title);
        GetInfo(0, id);
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
                Intent intent = new Intent(ProjectInfoActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });

        // 接收广播,刷新数据
        ProjectInfoActivity.this.registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                publicAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));
    }

    private void GetInfo(final int page, final int id) {
        if (Initialize) {
            progressDialog = ProgressDialog.show(ProjectInfoActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.get(RequestURL.projectInfoPage(page, id), new MyCall() {
            @Override
            public void success(String json) {
                Gson gson = new Gson();
                publicBean = gson.fromJson(json, PublicBean.class);
                if (publicBean.getErrorCode() == 0) {
                    if (dataList == null || page == 0) {
                        dataList = publicBean.getData().getDatas();
                        publicAdapter = new PublicAdapter(ProjectInfoActivity.this,dataList,"ProjectInfoActivity");
                        listView.setDivider(null);
                        listView.setAdapter(publicAdapter);
                    } else {
                        dataList.addAll(publicBean.getData().getDatas());
                        publicAdapter.notifyDataSetChanged();
                    }
                    state = true;
                    Initialize = false;
                    progressDialog.dismiss();
                } else {
                    state = false;
                    Initialize = false;
                    progressDialog.dismiss();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                Initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(ProjectInfoActivity.this, "请求失败,请检查网络");
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        textTitle = (TextView) findViewById(R.id.title);
        listView = (ListView) findViewById(R.id.listView);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
    }

    private void TheRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            // 下拉刷新
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                GetInfo(0, id);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(ProjectInfoActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(ProjectInfoActivity.this, "刷新失败,请检查网络");
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            // 上拉加载
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    GetInfo(publicBean.getData().getCurPage(), id);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(ProjectInfoActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(ProjectInfoActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(ProjectInfoActivity.this, "我是有底线的");
                }
            }
        });
    }
}
