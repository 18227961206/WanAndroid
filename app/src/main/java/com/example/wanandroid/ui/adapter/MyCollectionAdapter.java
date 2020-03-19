package com.example.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.MyCollectionBean;
import com.example.wanandroid.ui.bean.StateBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.GetCookie;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyCollectionAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:57
 * @Description: 我的收藏数据适配
 * @version: 1.1.5
 */

public class MyCollectionAdapter extends BaseAdapter {

    private List<MyCollectionBean.DataBean.DatasBean> dataList;
    private Context mContext;
    private TextView shareUser;
    private TextView title;
    private TextView superChapterName;
    private TextView niceDate;
    private TextView xin;
    private ImageView praise;
    private Animation animation;
    private Map<Integer, Boolean> isFrist;
    private SharedPreferencesCookieInfo collection;

    @SuppressLint("UseSparseArrays")
    public MyCollectionAdapter(Context context, List<MyCollectionBean.DataBean.DatasBean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
        collection = new SharedPreferencesCookieInfo(mContext, "collection");
        animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top);
        isFrist = new HashMap<Integer, Boolean>();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_official_accounts_info, null);
        } else {
            view = convertView;
        }

        shareUser = (TextView) view.findViewById(R.id.shareUser);
        title = (TextView) view.findViewById(R.id.title);
        superChapterName = (TextView) view.findViewById(R.id.superChapterName);
        niceDate = (TextView) view.findViewById(R.id.niceDate);
        xin = (TextView) view.findViewById(R.id.xin);
        praise = (ImageView) view.findViewById(R.id.praise);

        if (dataList.get(position).getAuthor().equals("")) {
            shareUser.setText("匿名");
        } else {
            shareUser.setText(dataList.get(position).getAuthor());
        }
        title.setText(dataList.get(position).getTitle());
        superChapterName.setText(dataList.get(position).getChapterName());
        niceDate.setText(dataList.get(position).getNiceDate());
        praise.setImageResource(R.drawable.ic_praise_selected);
        praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelTheCollection(position);
            }
        });

        // 如果是第一次加载该view，则使用动画
        if (isFrist.get(position) == null || isFrist.get(position)) {
            view.startAnimation(animation);
            isFrist.put(position, false);
        }

        return view;
    }

    private void CancelTheCollection(final int position) {
        OkHttpRequest.loginRequiredOperationPost(RequestURL.myCancelTheCollection(
                dataList.get(position).getId(), dataList.get(position).getOriginId()),
                GetCookie.getCookie(mContext), new MyCall() {
                    @Override
                    public void success(String json) {
                        try {
                            Gson gson = new Gson();
                            StateBean stateBean = gson.fromJson(json, StateBean.class);
                            if (stateBean.getErrorCode() == 0) {
                                collection.remove(dataList.get(position).getOriginId() + "");
                                dataList.remove(position);
                                notifyDataSetChanged();
                                MyToast.myToast(mContext, "已取消收藏");
                            } else {
                                MyToast.myToast(mContext, "取消收藏失败,请检查网络是否可用");
                            }
                        } catch (Exception e) {
                            MyToast.myToast(mContext, "取消收藏失败,请检查网络是否可用");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(IOException e) {
                        MyToast.myToast(mContext, "取消收藏失败,请检查网络是否可用");
                    }
                });
    }

}
