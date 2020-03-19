package com.example.wanandroid.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.SystemOneBean;
import com.example.wanandroid.ui.system.SystemOneInfoActivity;
import com.example.wanandroid.ui.tool.SetListViewHeight;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemOneContentAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 11:00
 * @Description: 系统右侧数据适配
 * @version: 1.1.5
 */

public class SystemOneContentAdapter extends BaseAdapter {

    private List<SystemOneBean.DataBean> systemList;
    private List<SystemOneBean.DataBean.ChildrenBean> childrenBeanList;
    private Context mContext;
    private SystemOneContentItemAdapter systemOneContentItemAdapter;

    public SystemOneContentAdapter(List<SystemOneBean.DataBean> systemList, Context context) {
        this.systemList = systemList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return systemList.size();
    }

    @Override
    public Object getItem(int position) {
        return systemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_system_one_content, null);
        } else {
            view = convertView;
        }

        TextView title = view.findViewById(R.id.content);
        title.setText(systemList.get(position).getName());

        GridView info = view.findViewById(R.id.info);
        childrenBeanList = systemList.get(position).getChildren();
        systemOneContentItemAdapter = new SystemOneContentItemAdapter(mContext, childrenBeanList);
        info.setAdapter(systemOneContentItemAdapter);

        SetListViewHeight.setGridViewHeight(info);

        info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(mContext, SystemOneInfoActivity.class);
                intent.putExtra("title", systemList.get(position).getChildren().get(i).getName());
                intent.putExtra("id", systemList.get(position).getChildren().get(i).getId());
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}