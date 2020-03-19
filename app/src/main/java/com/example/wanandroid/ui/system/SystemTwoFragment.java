package com.example.wanandroid.ui.system;

import android.os.Bundle;
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
import com.example.wanandroid.ui.adapter.SystemTwoContentAdapter;
import com.example.wanandroid.ui.adapter.SystemTwoTitleAdapter;
import com.example.wanandroid.ui.bean.SystemTwoBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemTwoFragment
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:38
 * @Description: 导航
 * @version: 1.1.5
 */

public class SystemTwoFragment extends Fragment {

    private ListView listViewTitle;
    private ListView listViewContent;

    private SystemTwoBean systemTwoBean;
    private List<SystemTwoBean.DataBean> systemList;
    private SystemTwoTitleAdapter systemTwoTitleAdapter;
    private SystemTwoContentAdapter systemTwoContentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_two, container, false);
        initView(view);
        SystemOneInfo();
        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewContent.setSelection(position); //点击左边与右边联动
                systemTwoTitleAdapter.setSelectedPosition(position);
            }
        });
        return view;
    }

    private void initView(View view) {
        listViewTitle = (ListView) view.findViewById(R.id.listViewTitle);
        listViewContent = (ListView) view.findViewById(R.id.listViewContent);
    }

    private void SystemOneInfo() {
        OkHttpRequest.get(RequestURL.systemTwoPage(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    systemTwoBean = gson.fromJson(json, SystemTwoBean.class);
                    if (systemTwoBean.getErrorCode() == 0) {
                        systemList = systemTwoBean.getData();

                        systemTwoTitleAdapter = new SystemTwoTitleAdapter(systemList, getContext());
                        listViewTitle.setDivider(null);
                        listViewTitle.setAdapter(systemTwoTitleAdapter);
                        systemTwoTitleAdapter.notifyDataSetChanged();

                        systemTwoContentAdapter = new SystemTwoContentAdapter(systemList, getContext());
                        listViewContent.setDivider(null);
                        listViewContent.setAdapter(systemTwoContentAdapter);
                        systemTwoContentAdapter.notifyDataSetChanged();

                        //右边滑动与左边联动
                        listViewContent.setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                            }

                            @Override
                            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                systemTwoTitleAdapter.setSelectedPosition(firstVisibleItem);
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
                MyToast.myToast(getContext(), "请求失败,请检查网络");
            }
        });
    }

}