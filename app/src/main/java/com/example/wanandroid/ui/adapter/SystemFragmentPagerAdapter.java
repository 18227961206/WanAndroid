package com.example.wanandroid.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemFragmentPagerAdapter
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:59
 * @Description: 体系Fragment适配
 * @version: 1.1.5
 */

public class SystemFragmentPagerAdapter extends FragmentPagerAdapter {

    private static String[] TAB_TITLE = new String[]{"体系", "导航"};
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;

    public SystemFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLE[position];
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}