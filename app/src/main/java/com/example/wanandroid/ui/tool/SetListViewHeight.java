package com.example.wanandroid.ui.tool;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

/**
 * 针对ScrollView与ListView嵌套显示与滑动问题进行处理
 * 对ListView高度重新计算
 * 针对ListView与GridView嵌套显示与滑动问题进行处理
 * 对GridView高度重新计算
 */

public class SetListViewHeight {

    public static void setListViewHeight(ListView listView, int count) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 总高度
        int totalHeight = 0;
        // listAdapter.getCount()返回数据项的数目
        for (int i = 1, len = listAdapter.getCount(); i < len - count; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + 60;
        listView.setLayoutParams(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void setGridViewHeight(GridView gridView) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 总高度
        int totalHeight = 0;
        // item上下间距
        int getVerticalSpacing = 0;
        // listAdapter.getCount()返回数据项的数目
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            if ((i + 1) % 2 != 0) {
                View listItem = listAdapter.getView(i, null, gridView);
                // 计算子项View的宽高
                listItem.measure(0, 0);
                // 统计奇数子项的总高度
                totalHeight += listItem.getMeasuredHeight();
                // 统计奇数子项上下间距的总高度
                getVerticalSpacing += gridView.getVerticalSpacing();
            }
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // params.height最后得到整个gridView完整显示需要的高度
        params.height = totalHeight + getVerticalSpacing;
        gridView.setLayoutParams(params);
    }
}
