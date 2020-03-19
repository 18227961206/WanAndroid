package com.example.wanandroid.ui.me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.ScoreLeaderBoardAdapter;
import com.example.wanandroid.ui.bean.ScoreLeaderBoardBean;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: ScoreLeaderBoardActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:50
 * @Description: 积分排行榜
 * @version: 1.1.5
 */

public class ScoreLeaderBoardActivity extends AppCompatActivity {

    private RefreshLayout refreshLayout;
    private ListView listView;
    private ImageView goBack;
    private ScoreLeaderBoardBean scoreLeaderBoardBean;
    private ScoreLeaderBoardAdapter scoreLeaderBoardAdapter;
    private List<ScoreLeaderBoardBean.DataBean.DatasBean> dataList;

    private Boolean state = false;

    private ProgressDialog progressDialog;
    private Boolean initialize = true;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_score_leader_board);
        initView();
        ScoreLeaderBoardInfo(1);
        TheRefresh();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        goBack = (ImageView) findViewById(R.id.goBack);
    }

    private void ScoreLeaderBoardInfo(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(ScoreLeaderBoardActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.get(RequestURL.scoreLeaderBoard(page), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    scoreLeaderBoardBean = gson.fromJson(json, ScoreLeaderBoardBean.class);
                    if (scoreLeaderBoardBean.getErrorCode() == 0) {
                        if (dataList == null || page == 1) {
                            dataList = scoreLeaderBoardBean.getData().getDatas();
                            scoreLeaderBoardAdapter = new ScoreLeaderBoardAdapter(ScoreLeaderBoardActivity.this, dataList);
                            //listView.setDivider(null);
                            listView.setAdapter(scoreLeaderBoardAdapter);
                        } else {
                            dataList.addAll(scoreLeaderBoardBean.getData().getDatas());
                            scoreLeaderBoardAdapter.notifyDataSetChanged();
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
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(ScoreLeaderBoardActivity.this,"请求失败,请检查网络");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                ScoreLeaderBoardInfo(1);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(ScoreLeaderBoardActivity.this,"刷新成功");
                } else {
                    MyToast.myToast(ScoreLeaderBoardActivity.this,"刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (scoreLeaderBoardBean.getData().getCurPage() <= scoreLeaderBoardBean.getData().getPageCount()) {
                    ScoreLeaderBoardInfo(scoreLeaderBoardBean.getData().getCurPage() + 1);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(ScoreLeaderBoardActivity.this,"加载成功");
                            } else {
                                MyToast.myToast(ScoreLeaderBoardActivity.this,"加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(ScoreLeaderBoardActivity.this,"我是有底线的");
                }
            }
        });
    }
}
