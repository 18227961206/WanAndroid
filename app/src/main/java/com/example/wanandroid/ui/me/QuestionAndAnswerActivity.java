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
import android.widget.ListView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.bean.PublicBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

public class QuestionAndAnswerActivity extends AppCompatActivity {

    private ImageView goBack;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;

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
        setContentView(R.layout.activity_question_and_answer);
        initView();
        QuestionAndAnswer(1);
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
                Intent intent = new Intent(QuestionAndAnswerActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });

        QuestionAndAnswerActivity.this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, final Intent intent) {
                publicAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void QuestionAndAnswer(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(QuestionAndAnswerActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.get(RequestURL.questionAndAnswer(page), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    publicBean = gson.fromJson(json, PublicBean.class);
                    if (publicBean.getErrorCode() == 0) {
                        if (dataList == null || page == 1) {
                            dataList = publicBean.getData().getDatas();
                            publicAdapter = new PublicAdapter(QuestionAndAnswerActivity.this, dataList, "QuestionAndAnswerActivity");
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
                        MyToast.myToast(QuestionAndAnswerActivity.this, "请求失败,请检查网络是否可用");
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    MyToast.myToast(QuestionAndAnswerActivity.this, "请求失败,请检查网络是否可用");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(QuestionAndAnswerActivity.this, "请求失败,请检查网络是否可用");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                QuestionAndAnswer(1);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(QuestionAndAnswerActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(QuestionAndAnswerActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    QuestionAndAnswer(publicBean.getData().getCurPage() + 1);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(QuestionAndAnswerActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(QuestionAndAnswerActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(QuestionAndAnswerActivity.this, "我是有底线的");
                }
            }
        });
    }
}
