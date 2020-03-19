package com.example.wanandroid.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.SystemOneBean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemOneContentItemAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 11:00
 * @Description: 体系右侧数据适配
 * @version: 1.1.5
 */

public class SystemOneContentItemAdapter extends BaseAdapter {

    private List<SystemOneBean.DataBean.ChildrenBean> systemList;
    private Context mContext;

    SystemOneContentItemAdapter(Context context, List<SystemOneBean.DataBean.ChildrenBean> systemList) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_system_one_content_item, null);
        } else {
            view = convertView;
        }

        TextView item = view.findViewById(R.id.content);
        item.setText(systemList.get(position).getName());

        return view;
    }
}