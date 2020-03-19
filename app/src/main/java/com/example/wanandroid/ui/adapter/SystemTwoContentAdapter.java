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
import com.example.wanandroid.ui.bean.SystemTwoBean;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.SetListViewHeight;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemTwoContentAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 11:02
 * @Description: 导航右侧数据适配
 * @version: 1.1.5
 */

public class SystemTwoContentAdapter extends BaseAdapter {

    private List<SystemTwoBean.DataBean> systemList;
    private List<SystemTwoBean.DataBean.ArticlesBean> childrenBeanList;
    private Context mContext;
    private SystemTwoContentItemAdapter systemTwoContentItemAdapter;

    public SystemTwoContentAdapter(List<SystemTwoBean.DataBean> systemList, Context context) {
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
            view = View.inflate(mContext, R.layout.item_system_two_content, null);
        } else {
            view = convertView;
        }

        TextView title = view.findViewById(R.id.content);
        title.setText(systemList.get(position).getName());

        GridView info = view.findViewById(R.id.info);
        childrenBeanList = systemList.get(position).getArticles();
        systemTwoContentItemAdapter = new SystemTwoContentItemAdapter(mContext, childrenBeanList);
        info.setAdapter(systemTwoContentItemAdapter);

        SetListViewHeight.setGridViewHeight(info);

        info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("title", systemList.get(position).getArticles().get(i).getTitle());
                intent.putExtra("link", systemList.get(position).getArticles().get(i).getLink());
                intent.putExtra("id", systemList.get(position).getArticles().get(i).getId());
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}