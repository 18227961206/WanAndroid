package com.example.wanandroid.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.SystemOneBean;
import com.example.wanandroid.ui.bean.SystemTwoBean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemTwoContentItemAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 11:02
 * @Description: 导航右侧数据适配
 * @version: 1.1.5
 */

public class SystemTwoContentItemAdapter extends BaseAdapter {

    private List<SystemTwoBean.DataBean.ArticlesBean> systemList;
    private Context mContext;

    SystemTwoContentItemAdapter(Context context, List<SystemTwoBean.DataBean.ArticlesBean> systemList) {
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
            view = View.inflate(mContext, R.layout.item_system_two_content_item, null);
        } else {
            view = convertView;
        }

        TextView item = view.findViewById(R.id.content);
        item.setText(systemList.get(position).getTitle());

        return view;
    }
}