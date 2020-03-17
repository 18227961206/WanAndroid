package com.example.wanandroid.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.MainActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.StartPageActivity;
import com.example.wanandroid.ui.bean.SearchForWordsBean;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private ImageView goBack;
    private EditText keyword;
    private ImageView search;
    private GridView info;
    private TextView clear;
    private ListView infoHistory;
    private TextView prompt;

    private SearchForWordsBean searchForWordsBean;
    private SearchForWordsAdapter searchForWordsAdapter;
    private List<SearchForWordsBean.DataBean> dataList;

    private String[] color = new String[]{"#686640", "#079A27", "#53A1B7", "#370F2A", "#2ABA74", "#5D440E", "#18891F", "#0D0181", "#612713"};

    private List<String> searchHistory = new ArrayList<String>();
    private SearchHistoryAdapter searchHistoryAdapter;
    private SharedPreferencesCookieInfo sp;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        initView();
        SearchForWords();
        SearchHistory();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 热门搜索点击事件
        info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sp.setSharedPreference(dataList.get(position).getName(), dataList.get(position).getName());
                sp.setSharedPreference("state", "state");
                Intent intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
                intent.putExtra("keyword", dataList.get(position).getName());
                startActivity(intent);
                SearchHistory();
            }
        });

        // 输入框搜索点击事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(keyword.getText())) {
                    sp.setSharedPreference(keyword.getText().toString(), keyword.getText().toString());
                    sp.setSharedPreference("state", "state");
                    Intent intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
                    intent.putExtra("keyword", keyword.getText().toString());
                    startActivity(intent);
                    SearchHistory();
                } else {
                    MyToast.myToast(SearchActivity.this, "请输入关键词");
                }
            }
        });

        // 搜索历史点击事件
        infoHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
                intent.putExtra("keyword", searchHistory.get(position));
                startActivity(intent);
            }
        });

        // 清空点击事件
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHistory.clear();
                sp.clear();
                SearchHistory();
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        keyword = (EditText) findViewById(R.id.keyword);
        search = (ImageView) findViewById(R.id.search);
        info = (GridView) findViewById(R.id.info);
        clear = (TextView) findViewById(R.id.clear);
        infoHistory = (ListView) findViewById(R.id.infoHistory);
        prompt = (TextView) findViewById(R.id.prompt);

        sp = new SharedPreferencesCookieInfo(SearchActivity.this, "SearchHistory");

        // 搜索框自动聚焦调出键盘
        keyword.setFocusable(true);
        keyword.setFocusableInTouchMode(true);
        keyword.requestFocus();
    }

    @SuppressLint("ApplySharedPref")
    private void SearchHistory() {
        // 读取
        if (sp.contain("state")) {
            prompt.setVisibility(View.GONE);
            infoHistory.setVisibility(View.VISIBLE);
            searchHistory.clear();
            Map<String, ?> stringMap = sp.getAll();
            for (Object value : stringMap.values()) {
                if (!value.toString().equals("state")) {
                    searchHistory.add(value.toString());
                }
            }
            // 延迟500毫秒数据适配
            handler = new Handler();
            handler.postDelayed(runnable = new Runnable() {
                @Override
                public void run() {
                    searchHistoryAdapter = new SearchHistoryAdapter();
                    infoHistory.setAdapter(searchHistoryAdapter);
                    infoHistory.setDivider(null);
                    searchHistoryAdapter.notifyDataSetChanged();
                }
            }, 500);
        } else {
            infoHistory.setVisibility(View.GONE);
            prompt.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 数据适配
     */
    private void SearchForWords() {
        OkHttpRequest.get(RequestURL.searchForWords(), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    searchForWordsBean = gson.fromJson(json, SearchForWordsBean.class);
                    if (searchForWordsBean.getErrorCode() == 0) {
                        dataList = searchForWordsBean.getData();
                        searchForWordsAdapter = new SearchForWordsAdapter();
                        info.setAdapter(searchForWordsAdapter);
                        searchForWordsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {

            }
        });
    }

    public class SearchForWordsAdapter extends BaseAdapter {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(SearchActivity.this, R.layout.item_search_for_words, null);
            } else {
                view = convertView;
            }
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(dataList.get(position).getName());
            name.setTextColor(Color.parseColor(color[position]));
            return view;
        }
    }

    public class SearchHistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return searchHistory.size();
        }

        @Override
        public Object getItem(int position) {
            return searchHistory.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(SearchActivity.this, R.layout.item_search_history, null);
            } else {
                view = convertView;
            }
            TextView name = (TextView) view.findViewById(R.id.item);
            ImageView deletes = (ImageView) view.findViewById(R.id.deletes);
            name.setText(searchHistory.get(position));
            deletes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp.remove(searchHistory.get(position));
                    if (searchHistory.size() == 1){
                        searchHistory.clear();
                        sp.clear();
                    }
                    SearchHistory();
                }
            });
            return view;
        }
    }
}
