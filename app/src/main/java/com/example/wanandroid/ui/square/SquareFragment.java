package com.example.wanandroid.ui.square;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import java.util.Objects;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SquareFragment
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:39
 * @Description: 广场
 * @version: 1.1.5
 */

public class SquareFragment extends Fragment {

    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private PublicBean publicBean;
    private PublicAdapter publicAdapter;
    private List<PublicBean.DataBean.DatasBean> dataList;
    private Boolean state = false;
    private Handler handler;
    private Runnable runnable;

    @SuppressLint("NewApi")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        initView(view);
        SquareInfo(0);
        TheRefresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });

        // 接收广播,刷新数据
        Objects.requireNonNull(getContext()).registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                publicAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));

        return view;
    }

    private void SquareInfo(final int page) {
        OkHttpRequest.get(RequestURL.square(page), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    publicBean = gson.fromJson(json, PublicBean.class);
                    if (publicBean.getErrorCode() == 0) {
                        if (dataList == null || page == 0) {
                            dataList = publicBean.getData().getDatas();
                            publicAdapter = new PublicAdapter(getContext(), dataList, "SquareFragment");
                            listView.setDivider(null);
                            listView.setAdapter(publicAdapter);
                        } else {
                            dataList.addAll(publicBean.getData().getDatas());
                            publicAdapter.notifyDataSetChanged();
                        }
                        state = true;
                    } else {
                        state = false;
                    }
                } catch (Exception e) {
                    state = false;
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                MyToast.myToast(getContext(), "请求失败,请检查网络");
            }
        });
    }

    private void initView(View view) {
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                SquareInfo(0);
                refreshlayout.finishRefresh(state);// 结束刷新
                if (state) {
                    MyToast.myToast(getContext(), "刷新成功");
                } else {
                    MyToast.myToast(getContext(), "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    SquareInfo(publicBean.getData().getCurPage());
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(getContext(), "加载成功");
                            } else {
                                MyToast.myToast(getContext(), "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(getContext(), "我是有底线的");
                }
            }
        });
    }
}