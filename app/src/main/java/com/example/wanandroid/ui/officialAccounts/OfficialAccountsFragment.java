package com.example.wanandroid.ui.officialAccounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.OfficialAccountsAdapter;
import com.example.wanandroid.ui.bean.OfficialAccountsBean;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: OfficialAccountsFragment
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:41
 * @Description: 公众号
 * @version: 1.1.5
 */

public class OfficialAccountsFragment extends Fragment {

    private RefreshLayout refreshLayout;
    private ListView listView;

    private OfficialAccountsBean officialAccountsBean;
    private OfficialAccountsAdapter officialAccountsAdapter;
    private List<OfficialAccountsBean.DataBean> officialAccountsList;

    private Boolean state = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official_accounts, container, false);
        initView(view);
        OfficialAccountsInfo();
        TheRefresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), OfficialAccountsInfoActivity.class);
                intent.putExtra("title", officialAccountsList.get(position).getName());
                intent.putExtra("id", officialAccountsList.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    private void OfficialAccountsInfo() {
        OkHttpRequest.get(RequestURL.officialAccounts(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    officialAccountsBean = gson.fromJson(json, OfficialAccountsBean.class);
                    if (officialAccountsBean.getErrorCode() == 0) {
                        officialAccountsList = officialAccountsBean.getData();
                        officialAccountsAdapter = new OfficialAccountsAdapter(officialAccountsList, getContext());
                        listView.setDivider(null);
                        listView.setAdapter(officialAccountsAdapter);
                        officialAccountsAdapter.notifyDataSetChanged();
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
                MyToast.myToast(getContext(), "请求失败,请检查网络是否可用");
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void TheRefresh() {
        refreshLayout.setEnableLoadMore(false); // 不启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                OfficialAccountsInfo();
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(getContext(), "刷新成功");
                } else {
                    MyToast.myToast(getContext(), "刷新失败,请检查网络是否可用");
                }
            }
        });
    }

}