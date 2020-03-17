package com.example.wanandroid.ui.me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.publicActivity.DetailsActivity;
import com.example.wanandroid.ui.tool.DataCleanManager;
import com.example.wanandroid.ui.tool.MyToast;

public class SettingsActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView title;
    private Switch noPIC;
    private Switch top;
    private Switch themeColors;
    private Switch navigationBar;
    private LinearLayout clearTheCache;
    private TextView clearTheCacheTextView;
    private LinearLayout version;
    private LinearLayout website;
    private LinearLayout sourceCode;
    private LinearLayout copyright;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        initView();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 无图模式
        noPIC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    MyToast.myToast(SettingsActivity.this, "已开启");
                }else {
                    MyToast.myToast(SettingsActivity.this, "已关闭");
                }
            }
        });

        // 首页置顶文章
        top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    MyToast.myToast(SettingsActivity.this, "已开启");
                }else {
                    MyToast.myToast(SettingsActivity.this, "已关闭");
                }
            }
        });

        // 主题颜色
        themeColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    MyToast.myToast(SettingsActivity.this, "已开启");
                }else {
                    MyToast.myToast(SettingsActivity.this, "已关闭");
                }
            }
        });

        // 导航栏着色
        navigationBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    MyToast.myToast(SettingsActivity.this, "已开启");
                }else {
                    MyToast.myToast(SettingsActivity.this, "已关闭");
                }
            }
        });

        // 清除缓存
        clearTheCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearTheCache();
            }
        });

        // 版本
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(SettingsActivity.this)
                        .setMessage("已是最新版本")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });

        // 官方网站
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, DetailsActivity.class);
                intent.putExtra("title", "WanAndroid——每日推荐优质文章");
                intent.putExtra("link", "https://www.wanandroid.com/");
                startActivity(intent);
            }
        });

        // 源代码
        sourceCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, DetailsActivity.class);
                intent.putExtra("title", "WanAndroid——每日推荐优质文章");
                intent.putExtra("link", "https://www.wanandroid.com/");
                startActivity(intent);
            }
        });

        // 版权
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("版权声明")
                        .setMessage("本APP所使用的所有API均有【玩Android】网站提供,仅供学习交流,不可用于任何商业用途。" +
                                "\nCopyright © 2020 小天狼星版权所有")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
        noPIC = (Switch) findViewById(R.id.noPIC);
        top = (Switch) findViewById(R.id.top);
        themeColors = (Switch) findViewById(R.id.themeColors);
        navigationBar = (Switch) findViewById(R.id.navigationBar);
        clearTheCache = (LinearLayout) findViewById(R.id.clearTheCache);
        clearTheCacheTextView = (TextView) findViewById(R.id.clearTheCacheTextView);
        version = (LinearLayout) findViewById(R.id.version);
        website = (LinearLayout) findViewById(R.id.website);
        sourceCode = (LinearLayout) findViewById(R.id.sourceCode);
        copyright = (LinearLayout) findViewById(R.id.copyright);
        // 读取缓存
        Cache();
    }

    private void Cache() {
        try {
            String resultCache = DataCleanManager.getTotalCacheSize(SettingsActivity.this);
            clearTheCacheTextView.setText(resultCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ClearTheCache() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("确定要清除缓存吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.clearAllCache(SettingsActivity.this);
                        try {
                            String resultCache = DataCleanManager.getTotalCacheSize(SettingsActivity.this);
                            clearTheCacheTextView.setText(resultCache);
                            MyToast.myToast(SettingsActivity.this, "已清除缓存");
                        } catch (Exception e) {
                            MyToast.myToast(SettingsActivity.this, "清除缓存失败");
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
