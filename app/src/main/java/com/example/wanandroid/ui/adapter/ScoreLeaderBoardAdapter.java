package com.example.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.ScoreLeaderBoardBean;

import java.util.List;

public class ScoreLeaderBoardAdapter extends BaseAdapter {

    private Context mContext;
    private List<ScoreLeaderBoardBean.DataBean.DatasBean> dataList;

    private TextView id;
    private TextView name;
    private TextView integral;

    public ScoreLeaderBoardAdapter(Context context, List<ScoreLeaderBoardBean.DataBean.DatasBean> dataList) {
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
            view = View.inflate(mContext, R.layout.item_score_leader_board, null);
        } else {
            view = convertView;
        }
        id = (TextView) view.findViewById(R.id.id);
        name = (TextView) view.findViewById(R.id.name);
        integral = (TextView) view.findViewById(R.id.integral);
        id.setText(dataList.get(position).getRank() + "");
        name.setText(dataList.get(position).getUsername());
        integral.setText(dataList.get(position).getCoinCount() + "");
        return view;
    }
}
