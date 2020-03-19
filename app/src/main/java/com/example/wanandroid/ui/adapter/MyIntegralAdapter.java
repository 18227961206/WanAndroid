package com.example.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.MyIntegralBean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyIntegralAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:57
 * @Description: 我的积分数据适配
 * @version: 1.1.5
 */

public class MyIntegralAdapter extends BaseAdapter {

    private List<MyIntegralBean.DataBean.DatasBean> dataList;
    private Context mContext;
    private TextView name;
    private TextView info;
    private TextView integral;

    public MyIntegralAdapter(Context context, List<MyIntegralBean.DataBean.DatasBean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_my_integral, null);
        } else {
            view = convertView;
        }
        name = (TextView) view.findViewById(R.id.name);
        info = (TextView) view.findViewById(R.id.info);
        integral = (TextView) view.findViewById(R.id.integral);

        name.setText(dataList.get(position).getReason());
        info.setText(dataList.get(position).getDesc());
        integral.setText("+" + dataList.get(position).getCoinCount());
        return view;
    }
}
