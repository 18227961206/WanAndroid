package com.example.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.ProjectBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: ProjectAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:58
 * @Description: 项目数据适配
 * @version: 1.1.5
 */

public class ProjectAdapter extends BaseAdapter {

    private List<ProjectBean.DataBean> classificationBeanList;
    private Context mContext;

    private ImageView imgItem;
    private TextView titleItem;
    private TextView timeItem;
    private int[] img = new int[]{R.drawable.banner01, R.drawable.banner02, R.drawable.banner03,
            R.drawable.banner04, R.drawable.banner05, R.drawable.banner06, R.drawable.banner07,
            R.drawable.banner08
    };
    private int count = 0;

    public ProjectAdapter(List<ProjectBean.DataBean> classificationBeanList, Context mContext) {
        this.classificationBeanList = classificationBeanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return classificationBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return classificationBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_project, null);
        } else {
            view = convertView;
        }

        imgItem = (ImageView) view.findViewById(R.id.img_item);
        titleItem = (TextView) view.findViewById(R.id.title_item);
        timeItem = (TextView) view.findViewById(R.id.time_item);

        titleItem.setText(classificationBeanList.get(position).getName());
        timeItem.setText(CurrentTime());

        if (count < img.length) {
            imgItem.setImageResource(img[count]);
            count++;
        } else {
            count = 0;
        }

        return view;
    }

    @SuppressLint("SimpleDateFormat")
    private String CurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
