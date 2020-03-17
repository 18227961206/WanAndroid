package com.example.wanandroid.ui.publicActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.PublicAdapter;
import com.example.wanandroid.ui.bean.StateBean;
import com.example.wanandroid.ui.me.LoginActivity;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.tool.GetCookie;
import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.google.gson.Gson;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView titleView;
    private ImageView more;
    private WebView webView;
    private ProgressDialog progressDialog;
    private String link;
    private String titles;
    private int id;
    private SharedPreferencesCookieInfo collection;
    private Handler handler = new Handler();
    private Runnable runnable;
    private Boolean collectionOperation = false;
    public static final String action = "DetailsActivity";

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);
        initView();
        Intent getItemInfo = getIntent();
        titleView.setText(getItemInfo.getStringExtra("title"));
        link = getItemInfo.getStringExtra("link");
        id = getItemInfo.getIntExtra("id", 0);
        LinkInfo(link);
        webView.setWebViewClient(new WebViewClientEmb());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleView.setText(title);
                titles = title;
            }
        });
        //设置超时
       /* handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    MyToast.myToast(DetailsActivity.this, "连接超时");
                }
            }
        }, 15000);*/

        goBack.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Assert")
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出Menu菜单
                showPopupMenu(v);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (id != 0 && collectionOperation) {
            // 刷新数据
            PublicAdapter.theRefresh();
            // 发送广播
            sendBroadcast(new Intent(action));
        }
        super.onDestroy();
    }

    // 弹出Menu菜单
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().toString().equals("分享")) {
                    // 调用系统分享功能分享文章
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "玩Android分享【" + titles + "】: " + link);
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                } else if (item.getTitle().toString().equals("收藏")) {
                    if (id != 0) {
                        if (GetCookie.getCookie(DetailsActivity.this).equals("")) {
                            MyToast.myToast(DetailsActivity.this, "请先登录");
                            startActivity(new Intent(DetailsActivity.this, LoginActivity.class));
                        } else {
                            CollectionOperation(id);
                        }
                    }
                } else {
                    // 调用系统默认浏览器打开网址
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(link);
                    intent.setData(content_url);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });
        popupMenu.show();
    }

    // 收藏
    private void CollectionOperation(final int id) {
        OkHttpRequest.loginRequiredOperationPost(RequestURL.collectionStationArticles(id),
                GetCookie.getCookie(DetailsActivity.this), new MyCall() {
                    @Override
                    public void success(String json) {
                        try {
                            Gson gson = new Gson();
                            StateBean stateBean = gson.fromJson(json, StateBean.class);
                            if (stateBean.getErrorCode() == 0) {
                                collection.setSharedPreference(String.valueOf(id), id);
                                collectionOperation = true;
                                MyToast.myToast(DetailsActivity.this, "收藏成功");
                            } else {
                                MyToast.myToast(DetailsActivity.this, "收藏失败,请检查网络是否可用");
                            }
                        } catch (Exception e) {
                            MyToast.myToast(DetailsActivity.this, "收藏失败,请检查网络是否可用");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(IOException e) {
                        MyToast.myToast(DetailsActivity.this, "收藏失败,请检查网络是否可用");
                    }
                });
    }

    /**
     * WebView与ProgressDialog的使用
     *
     * @param link
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void LinkInfo(String link) {
        if (webView != null) {
            progressDialog = ProgressDialog.show(DetailsActivity.this, null, "页面加载中，请稍后...");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setLoadsImagesAutomatically(true);
            //设置加载进来的页面自适应手机屏幕
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            //支持javascript
            webSettings.setJavaScriptEnabled(true);
            //支持网页缓存
            webSettings.setDomStorageEnabled(true);
            //解决图片不显示
            webSettings.setBlockNetworkImage(false);
            // 设置可以支持缩放
            webSettings.setSupportZoom(true);
            // 设置出现缩放工具
            webSettings.setBuiltInZoomControls(true);
            //扩大比例的缩放
            webSettings.setUseWideViewPort(true);
            webView.loadUrl(link);
        }
    }

    public class WebViewClientEmb extends WebViewClient {
        // 页面载入前调用
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (!progressDialog.isShowing()) {
                progressDialog = ProgressDialog.show(DetailsActivity.this, null, "页面加载中，请稍后...");
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onPageFinished(WebView view, String url) {
            progressDialog.dismiss();
        }
    }

    // 监听系统返回键，如果有上个html则返回，否则退出这个界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        titleView = (TextView) findViewById(R.id.title);
        more = (ImageView) findViewById(R.id.more);
        webView = (WebView) findViewById(R.id.webView);
        collection = new SharedPreferencesCookieInfo(DetailsActivity.this, "collection");
    }
}
