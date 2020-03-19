package com.example.wanandroid.ui.system;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.SystemOneContentAdapter;
import com.example.wanandroid.ui.adapter.SystemOneTitleAdapter;
import com.example.wanandroid.ui.bean.SystemOneBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemOneFragment
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:37
 * @Description: 体系
 * @version: 1.1.5
 */

public class SystemOneFragment extends Fragment {

    private ListView listViewTitle;
    private ListView listViewContent;

    private SystemOneBean systemOneBean;
    private List<SystemOneBean.DataBean> systemList;
    private SystemOneTitleAdapter systemOneTitleAdapter;
    private SystemOneContentAdapter systemOneContentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_one, container, false);
        initView(view);
        SystemOneInfo();
        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewContent.setSelection(position); //点击左边与右边联动
                systemOneTitleAdapter.setSelectedPosition(position);
            }
        });
        return view;
    }

    private void initView(View view) {
        listViewTitle = (ListView) view.findViewById(R.id.listViewTitle);
        listViewContent = (ListView) view.findViewById(R.id.listViewContent);
    }

    private void SystemOneInfo() {
        OkHttpRequest.get(RequestURL.systemOnePage(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    systemOneBean = gson.fromJson(json, SystemOneBean.class);
                    if (systemOneBean.getErrorCode() == 0) {
                        systemList = systemOneBean.getData();

                        systemOneTitleAdapter = new SystemOneTitleAdapter(systemList, getContext());
                        listViewTitle.setDivider(null);
                        listViewTitle.setAdapter(systemOneTitleAdapter);
                        systemOneTitleAdapter.notifyDataSetChanged();

                        systemOneContentAdapter = new SystemOneContentAdapter(systemList, getContext());
                        listViewContent.setDivider(null);
                        listViewContent.setAdapter(systemOneContentAdapter);
                        systemOneContentAdapter.notifyDataSetChanged();

                        //右边滑动与左边联动
                        listViewContent.setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                            }

                            @Override
                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                systemOneTitleAdapter.setSelectedPosition(firstVisibleItem);
                                listViewTitle.setSelection(firstVisibleItem);
                                //listViewTitle.setSelectionFromTop(firstVisibleItem, 0);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                MyToast.myToast(getContext(), "请求失败,请检查网络是否可用");
            }
        });
    }

}