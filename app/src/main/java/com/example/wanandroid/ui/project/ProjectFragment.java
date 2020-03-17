package com.example.wanandroid.ui.project;

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
import com.example.wanandroid.ui.adapter.ProjectAdapter;
import com.example.wanandroid.ui.bean.ProjectBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

public class ProjectFragment extends Fragment {

    private RefreshLayout refreshLayout;
    private ListView listView;

    private ProjectBean projectBean;
    private ProjectAdapter projectAdapter;
    private List<ProjectBean.DataBean> classificationBeanList;

    private Boolean state = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        initView(view);
        ProjectInfo();
        TheRefresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ProjectInfoActivity.class);
                intent.putExtra("title", classificationBeanList.get(position).getName());
                intent.putExtra("id", classificationBeanList.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.listView);
    }

    private void ProjectInfo() {
        OkHttpRequest.get(RequestURL.projectPage(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    projectBean = gson.fromJson(json, ProjectBean.class);
                    if (projectBean.getErrorCode() == 0) {
                        classificationBeanList = projectBean.getData();
                        projectAdapter = new ProjectAdapter(classificationBeanList, getContext());
                        listView.setDivider(null);
                        listView.setAdapter(projectAdapter);
                        projectAdapter.notifyDataSetChanged();
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

    /**
     * 下拉刷新
     */
    private void TheRefresh() {
        refreshLayout.setEnableLoadMore(false); // 不启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                ProjectInfo();
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(getContext(), "刷新成功");
                } else {
                    MyToast.myToast(getContext(), "刷新失败,请检查网络");
                }
            }
        });
    }

}