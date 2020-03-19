package com.example.wanandroid.ui.me;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyTODOActivity
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:48
 * @Description: 我的TODO
 * @version: 1.1.5
 */

public class MyTODOActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView title;
    private ImageView exchange;
    public static int mIndex = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_todo);
        initView();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出Menu菜单
                showPopupMenu(v);
            }
        });
    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean processTouchEvent = SwipeHelper.instance().processTouchEvent(ev);
        if (processTouchEvent) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }*/

    private void initView() {
        goBack = (ImageView) findViewById(R.id.goBack);
        title = (TextView) findViewById(R.id.title);
        exchange = (ImageView) findViewById(R.id.exchange);
        title.setText("我只用这一个");
    }

    // 弹出Menu菜单
    private void showPopupMenu(View view) {
        final PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.exchange, popupMenu.getMenu());
        //popupMenu.getMenu().getItem(0).setChecked(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().toString().equals("我只用这一个")) {
                    title.setText(item.getTitle().toString());
                } else if (item.getTitle().toString().equals("工作")) {
                    title.setText(item.getTitle().toString());
                } else if (item.getTitle().toString().equals("学习")) {
                    title.setText(item.getTitle().toString());
                } else {
                    title.setText(item.getTitle().toString());
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
}
