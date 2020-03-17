package com.example.wanandroid.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.bean.PublicBean;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.MyToast;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

public class SearchInfoActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView titleView;
    private SmartRefreshLayout refreshLayout;
    private LinearLayout no_data;
    private ListView listView;
    private String keyword;
    private PublicBean publicBean;
    private PublicAdapter publicAdapter;
    private List<PublicBean.DataBean.DatasBean> dataList;
    private Boolean state = false;
    private ProgressDialog progressDialog;
    private Boolean initialize = true;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_info);
        initView();
        Intent getItemInfo = getIntent();
        keyword = getItemInfo.getStringExtra("keyword");
        titleView.setText(keyword);
        SearchInfo(0, keyword);
        TheRefresh();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchInfoActivity.this, DetailsActivity.class);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("link", dataList.get(position).getLink());
                intent.putExtra("id", dataList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        titleView = (TextView) findViewById(R.id.title);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        no_data = (LinearLayout) findViewById(R.id.no_data);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void SearchInfo(final int page, final String keyword) {
        if (initialize) {
            progressDialog = ProgressDialog.show(SearchInfoActivity.this, null, "数据加载中，请稍后...");
        }
        OkHttpRequest.post(RequestURL.search(page, keyword), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    publicBean = gson.fromJson(json, PublicBean.class);
                    if (publicBean.getErrorCode() == 0) {
                        if (publicBean.getData().getDatas().size() != 0) {
                            refreshLayout.setVisibility(View.VISIBLE);
                            no_data.setVisibility(View.GONE);
                            if (dataList == null || page == 0) {
                                dataList = publicBean.getData().getDatas();
                                publicAdapter = new PublicAdapter(SearchInfoActivity.this, dataList, "SearchInfoActivity");
                                listView.setDivider(null);
                                listView.setAdapter(publicAdapter);
                            } else {
                                dataList.addAll(publicBean.getData().getDatas());
                                publicAdapter.notifyDataSetChanged();
                            }
                            state = true;
                            initialize = false;
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            refreshLayout.setVisibility(View.GONE);
                            no_data.setVisibility(View.VISIBLE);
                        }
                    } else {
                        state = false;
                        initialize = false;
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    state = false;
                    initialize = false;
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                state = false;
                initialize = false;
                progressDialog.dismiss();
                MyToast.myToast(SearchInfoActivity.this, "请求失败,请检查网络");
            }
        });
    }

    private void TheRefresh() {
        // 下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                SearchInfo(0, keyword);
                refreshlayout.finishRefresh(state);
                if (state) {
                    MyToast.myToast(SearchInfoActivity.this, "刷新成功");
                } else {
                    MyToast.myToast(SearchInfoActivity.this, "刷新失败,请检查网络");
                }
            }
        });

        // 上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshlayout) {
                if (publicBean.getData().getCurPage() < publicBean.getData().getPageCount()) {
                    SearchInfo(publicBean.getData().getCurPage(), keyword);
                    handler = new Handler();
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            refreshlayout.finishLoadMore(state);
                            if (state) {
                                MyToast.myToast(SearchInfoActivity.this, "加载成功");
                            } else {
                                MyToast.myToast(SearchInfoActivity.this, "加载失败,请检查网络");
                            }
                        }
                    }, 500);
                } else {
                    refreshlayout.finishRefresh(true);
                    refreshLayout.closeHeaderOrFooter();
                    MyToast.myToast(SearchInfoActivity.this, "我是有底线的");
                }
            }
        });
    }
}
