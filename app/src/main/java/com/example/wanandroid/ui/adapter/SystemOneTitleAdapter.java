package com.example.wanandroid.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.SystemOneBean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemOneTitleAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 11:01
 * @Description: 体系左侧数据适配
 * @version: 1.1.5
 */

public class SystemOneTitleAdapter extends BaseAdapter {

    private List<SystemOneBean.DataBean> systemList;
    private Context mContext;

    private int selectedPosition = 0;// 选中的位置

    public SystemOneTitleAdapter(List<SystemOneBean.DataBean> systemList, Context context) {
        this.systemList = systemList;
        this.mContext = context;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
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
            view = View.inflate(mContext, R.layout.item_system_one_title, null);
        } else {
            view = convertView;
        }

        TextView title = view.findViewById(R.id.title);
        title.setText(systemList.get(position).getName());

        if (selectedPosition == position) {
            title.setBackgroundColor(Color.WHITE);
            title.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            title.setBackgroundColor(mContext.getResources().getColor(R.color.color20));
            title.setTextColor(mContext.getResources().getColor(R.color.color05));
        }

        return view;
    }
}