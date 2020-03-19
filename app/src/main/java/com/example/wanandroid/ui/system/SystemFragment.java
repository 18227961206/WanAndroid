package com.example.wanandroid.ui.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.SystemFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: SystemFragment
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:37
 * @Description: 体系
 * @version: 1.1.5
 */

public class SystemFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system, container, false);
        initView(view);
        FragmentViewPager();
        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }

    private void FragmentViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SystemOneFragment());
        fragmentList.add(new SystemTwoFragment());
        SystemFragmentPagerAdapter adapter = new SystemFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}