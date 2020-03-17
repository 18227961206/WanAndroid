package com.example.wanandroid.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.adapter.TopAdapter;
import com.example.wanandroid.ui.bean.BannerBean;
import com.example.wanandroid.ui.bean.PublicBean;
import com.example.wanandroid.ui.bean.TopBean;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SetListViewHeight;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RefreshLayout refreshLayout;
    private ListView listView;
    private ListView top_listView;
    private Banner banner;
    private BannerBean bannerBean;
    private List<String> bannerImgUrl = new ArrayList<>();
    private List<String> bannerImgTitle = new ArrayList<>();
    private TopBean topBean;
    private TopAdapter topAdapter;
    private List<TopBean.DataBean> topList;
    private PublicBean publicBean;
    private PublicAdapter publicAdapter;
    private List<PublicBean.DataBean.DatasBean> dataList;
    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;
    private Handler handler;
    private Runnable runnable;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        BannerInfo();
        TopInfo();
        HomeInfo(0);
        TheRefresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });

        top_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("title", topList.get(position).getTitle());
                intent.putExtra("link", topList.get(position).getLink());
                intent.putExtra("id", topList.get(position).getId());
                startActivity(intent);
            }
        });

        // 接收广播,刷新数据
        Objects.requireNonNull(getContext()).registerReceiver(new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, final Intent intent) {
                publicAdapter.notifyDataSetChanged();
                TopAdapter.theRefresh();
                topAdapter.notifyDataSetChanged();
            }
        }, new IntentFilter(DetailsActivity.action));

        return view;
    }


    private void initView(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.listView);
        top_listView = (ListView) view.findViewById(R.id.top_listView);
        banner = (Banner) view.findViewById(R.id.banner);
    }

    /**
     * banner图
     */
    private void BannerInfo() {
        OkHttpRequest.get(RequestURL.homePageBanner(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    bannerBean = gson.fromJson(json, BannerBean.class);
                    if (bannerBean.getErrorCode() == 0) {
                        assert bannerBean != null;
                        List<BannerBean.DataBean> datasBeanList = bannerBean.getData();
                        bannerImgUrl.clear();
                        bannerImgTitle.clear();
                        for (BannerBean.DataBean item : datasBeanList) {
                            String imgeUri = item.getImagePath();
                            String title = item.getTitle();
                            bannerImgUrl.add(imgeUri);
                            bannerImgTitle.add(title);
                        }
                        Banners();
                    } else {
                        MyToast.myToast(getContext(), "banner图数据参数错误,请刷新再试");
                    }
                } catch (Exception e) {
                    MyToast.myToast(getContext(), "banner图数据解析失败,请刷新再试");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                MyToast.myToast(getContext(), "banner图获取失败,请检查网络是否可用");
            }
        });
    }

    /**
     * 首页置顶文章
     */
    private void TopInfo() {
        OkHttpRequest.get(RequestURL.topInfo(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    topBean = gson.fromJson(json, TopBean.class);
                    if (topBean.getErrorCode() == 0) {
                        topList = topBean.getData();
                        topAdapter = new TopAdapter(getContext(), topList);
                        top_listView.setDivider(null);
                        top_listView.setAdapter(topAdapter);
                        // 总高度
                        ListAdapter listAdapter = top_listView.getAdapter();
                        int totalHeight = 0;
                        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                            View listItem = listAdapter.getView(i, null, top_listView);
                            listItem.measure(0, 0);
                            totalHeight += listItem.getMeasuredHeight();
                        }
                        ViewGroup.LayoutParams params = top_listView.getLayoutParams();
                        params.height = totalHeight;
                        top_listView.setLayoutParams(params);
                    } else {
                        MyToast.myToast(getContext(), "获取置顶文章失败,请检查网络是否可用");
                    }
                } catch (Exception e) {
                    MyToast.myToast(getContext(), "获取置顶文章失败,请检查网络是否可用");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                MyToast.myToast(getContext(), "获取置顶文章失败,请检查网络是否可用");
            }
        });
    }

    /**
     * 首页文章列表
     *
     * @param page
     */
    private void HomeInfo(final int page) {
        if (initialize) {
            progressDialog = ProgressDialog.show(getContext(), null, "数据加载中，请稍后...");
        }
        OkHttpRequest.get(RequestURL.homePage(page), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    publicBean = gson.fromJson(json, PublicBean.class);
                    if (publicBean.getErrorCode() == 0) {
                        if (dataList == null || page == 0) {
                            dataList = publicBean.getData().getDatas();
                            publicAdapter = new PublicAdapter(getContext(), dataList, "HomeFragment");
                            listView.setDivider(null);
                            listView.setAdapter(publicAdapter);
                            SetListViewHeight.setListViewHeight(listView, publicBean.getData().getCurPage());
                        } else {
                            dataList.addAll(publicBean.getData().getDatas());
                            publicAdapter.notifyDataSetChanged();
                            SetListViewHeight.setListViewHeight(listView, publicBean.getData().getCurPage());
                        }
                        state = true;
                        initialize = false;
                        progressDialog.dismiss();
                    } else {
                        state = false;
                        initialize = false;
                        progressDialog.dismiss();
                        MyToast.myToast(getContext(), "请求失败,请检查网络是否可用");
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    MyToast.myToast(getContext(), "请求失败,请检查网络是否可用");
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(getContext(), "请求失败,请检查网络是否可用");
            }
        });
    }

    /**
     * 下拉刷新与上拉加载
     * Android智能翻转刷新框架，支持越界回弹，越界转变，具有极强的扩展性
     * https://github.com/scwang90/SmartRefreshLayout
     * implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0'
     */
    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                TopInfo();
                HomeInfo(0);
                refreshlayout.finishRefresh(state);// 结束刷新
                if (state) {
                    MyToast.myToast(getContext(), "刷新成功");
                } else {
                    MyToast.myToast(getContext(), "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    HomeInfo(publicBean.getData().getCurPage());
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(getContext(), "加载成功");
                            } else {
                                MyToast.myToast(getContext(), "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);// 结束加载
                    refreshLayout.closeHeaderOrFooter();// 关闭正在打开状态的 Header 或者 Footer
                    MyToast.myToast(getContext(), "我是有底线的");
                }
            }
        });
    }

    /**
     * Android广告图片轮播控件，Banner 2.0 全新升级！！！
     * https://github.com/youth5201314/banner/tree/release-1.4.10
     */
    private void Banners() {
        //设置内置样式，有多种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(bannerImgUrl);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.CubeOut);
        //设置轮播图的标题集合
        banner.setBannerTitles(bannerImgTitle);
        //设置轮播间隔时间
        banner.setDelayTime(4000);
        //设置是否为自动轮播，默认是“是”
        banner.isAutoPlay(true);
        //设置指示器的位置，小点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("title", bannerBean.getData().get(position).getTitle());
                        intent.putExtra("link", bannerBean.getData().get(position).getUrl());
                        startActivity(intent);
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    /**
     * 使用Glide定义的图片加载器
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}