package com.example.wanandroid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.wanandroid.ui.adapter.TabFragmentPagerAdapter;
import com.example.wanandroid.ui.bean.StateBean;
import com.example.wanandroid.ui.bean.UserInfoBean;
import com.example.wanandroid.ui.home.HomeFragment;
import com.example.wanandroid.ui.me.LoginActivity;
import com.example.wanandroid.ui.me.MyCollectionActivity;
import com.example.wanandroid.ui.me.MyIntegralActivity;
import com.example.wanandroid.ui.me.MyShareActivity;
import com.example.wanandroid.ui.me.MyTODOActivity;
import com.example.wanandroid.ui.me.QuestionAndAnswerActivity;
import com.example.wanandroid.ui.me.ScoreLeaderBoardActivity;
import com.example.wanandroid.ui.me.SettingsActivity;
import com.example.wanandroid.ui.network.MyCall;
import com.example.wanandroid.ui.network.OkHttpRequest;
import com.example.wanandroid.ui.network.RequestURL;
import com.example.wanandroid.ui.officialAccounts.OfficialAccountsFragment;
import com.example.wanandroid.ui.project.ProjectFragment;
import com.example.wanandroid.ui.search.SearchActivity;
import com.example.wanandroid.ui.square.SquareFragment;
import com.example.wanandroid.ui.system.SystemFragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.wanandroid.ui.tool.MyToast;
import com.example.wanandroid.ui.tool.SharedPreferencesCookieInfo;
import com.example.wanandroid.ui.tool.StringAndBitmap;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // 导航
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView appMore;
    private TextView appName;
    private ImageView appSearch;

    // 侧边栏头部
    private ImageView headPortrait;
    private LinearLayout loginLayout;
    private LinearLayout successful_layout;
    private TextView login;
    private TextView name;
    private TextView meInfo;
    private ImageView rankingList;

    private Menu menu;
    private SharedPreferencesCookieInfo sp;

    // 退出app
    private static final int TIME_EXIT = 2000;
    private long mBackPressed;

    // 导航
    private static int[] TAB_TITLE = new int[]{R.string.navigation_home, R.string.navigation_square,
            R.string.navigation_official_accounts, R.string.navigation_system, R.string.navigation_project,};
    private static int[] TAB_ICON = {R.drawable.ic_home, R.drawable.ic_square,
            R.drawable.ic_official_accounts, R.drawable.ic_system, R.drawable.ic_project
    };
    private static int[] TAB_ICON_SELECTED = {R.drawable.ic_home_selected, R.drawable.ic_square_selected,
            R.drawable.ic_official_accounts_selected, R.drawable.ic_system_selected,
            R.drawable.ic_project_selected
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        FragmentViewPager();

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
        }*/

        // 打开侧边栏
        appMore.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        // 侧边栏MenuItem监听事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (loginLayout.getVisibility() != View.VISIBLE) {
                    if (item.getTitle().toString().equals("我的积分")) {
                        startActivity(new Intent(MainActivity.this, MyIntegralActivity.class));
                    } else if (item.getTitle().toString().equals("我的收藏")) {
                        startActivity(new Intent(MainActivity.this, MyCollectionActivity.class));
                    } else if (item.getTitle().toString().equals("我的分享")) {
                        startActivity(new Intent(MainActivity.this, MyShareActivity.class));
                    } else if (item.getTitle().toString().equals("每日一问")) {
                        startActivity(new Intent(MainActivity.this, QuestionAndAnswerActivity.class));
                    } else if (item.getTitle().toString().equals("TODO")) {
                        startActivity(new Intent(MainActivity.this, MyTODOActivity.class));
                    } else if (item.getTitle().toString().equals("系统设置")) {
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                    } else {
                        Exit();
                    }
                } else {
                    MyToast.myToast(MainActivity.this, "请先登录");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                return false;
            }
        });

        // 对TabLayout进行监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 加载
            @SuppressLint({"NewApi", "ResourceAsColor"})
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                appName.setText(TAB_TITLE[tab.getPosition()]);
                // 方式一
                //Objects.requireNonNull(mTabLayout.getTabAt(tab.getPosition())).setText(TAB_TITLE[tab.getPosition()]).setIcon(TAB_ICON_SELECTED[tab.getPosition()]);
                // 方式二
                TextView textView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.nav_title);
                ImageView imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.nav_icon);
                textView.setTextColor(getResources().getColor(R.color.nav_selected));
                imageView.setImageResource(TAB_ICON_SELECTED[tab.getPosition()]);
            }

            // 消失
            @SuppressLint({"NewApi", "ResourceAsColor"})
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 方式一
                //Objects.requireNonNull(mTabLayout.getTabAt(tab.getPosition())).setText(TAB_TITLE[tab.getPosition()]).setIcon(TAB_ICON[tab.getPosition()]);
                //方式二
                TextView textView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.nav_title);
                ImageView imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.nav_icon);
                textView.setTextColor(getResources().getColor(R.color.nav));
                imageView.setImageResource(TAB_ICON[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 搜索事件
        appSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        // 去登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        // 积分排行榜
        rankingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScoreLeaderBoardActivity.class));
            }
        });

        // 上传头像
        headPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadThePicture();
            }
        });
    }

    private void initView() {
        // 导航
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab);
        appMore = (ImageView) findViewById(R.id.app_more);
        appName = (TextView) findViewById(R.id.app_name);
        appSearch = (ImageView) findViewById(R.id.app_search);
        // 侧边栏头部
        headPortrait = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.headPortrait);
        loginLayout = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.login_layout);
        successful_layout = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.successful_layout);
        login = (TextView) navigationView.getHeaderView(0).findViewById(R.id.login);
        name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        meInfo = (TextView) navigationView.getHeaderView(0).findViewById(R.id.me_info);
        rankingList = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ranking_list);
        // 获取侧边栏MenuItem
        menu = navigationView.getMenu();
        // 判断是否已经登录过了
        sp = new SharedPreferencesCookieInfo(MainActivity.this, "cookie");
        if (sp.getSharedPreference("cookie", "").toString().equals("")) {
            loginLayout.setVisibility(View.VISIBLE);
            successful_layout.setVisibility(View.GONE);
            menu.findItem(R.id.exit).setVisible(false);
        } else {
            loginLayout.setVisibility(View.GONE);
            successful_layout.setVisibility(View.VISIBLE);
            menu.findItem(R.id.exit).setVisible(true);
            name.setText(sp.getSharedPreference("username", "").toString());
            Integral();
            // 设置头像
            headPortrait.setImageBitmap(StringAndBitmap.stringToBitmap(MainActivity.this));
        }
    }

    /**
     * Fragment与ViewPager适配
     */
    @SuppressLint("NewApi")
    private void FragmentViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SquareFragment());
        fragmentList.add(new OfficialAccountsFragment());
        fragmentList.add(new SystemFragment());
        fragmentList.add(new ProjectFragment());
        //TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorHeight(0);

        // ViewPager底部导航设置上图标下文字    方式一
        /*mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        Objects.requireNonNull(mTabLayout.getTabAt(0)).setText(TAB_TITLE[0]).setIcon(TAB_ICON_SELECTED[0]);
        for (int i = 1; i < TAB_TITLE.length; i++) {
            Objects.requireNonNull(mTabLayout.getTabAt(i)).setText(TAB_TITLE[i]).setIcon(TAB_ICON[i]);
        }*/

        // ViewPager底部导航设置上图标下文字    方式二
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            Objects.requireNonNull(mTabLayout.getTabAt(i)).setCustomView(getTabView(i, TAB_TITLE[i], TAB_ICON[i]));
        }
    }

    /**
     * ViewPager自定义底部导航
     *
     * @param i
     * @param TAB_TITLE
     * @param TAB_ICON
     * @return
     */
    @SuppressLint("InflateParams")
    public View getTabView(int i, int TAB_TITLE, int TAB_ICON) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.nav_item, null);
        ImageView imageView = view.findViewById(R.id.nav_icon);
        TextView textView = view.findViewById(R.id.nav_title);
        textView.setText(TAB_TITLE);
        if (i == 0) {
            imageView.setImageResource(TAB_ICON_SELECTED[0]);
            textView.setTextColor(getResources().getColor(R.color.nav_selected));
        } else {
            imageView.setImageResource(TAB_ICON);
        }
        return view;
    }

    /**
     * 个人积分
     */
    private void Integral() {
        OkHttpRequest.loginRequiredOperationGet(RequestURL.userInfo(), sp.getSharedPreference("cookie", "").toString(), new MyCall() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    UserInfoBean userInfoBean = gson.fromJson(json, UserInfoBean.class);
                    if (userInfoBean.getErrorCode() == 0) {
                        meInfo.setText("等级: " + userInfoBean.getData().getLevel() + "  排名: " + userInfoBean.getData().getRank());
                        sp.setSharedPreference("coinCount", userInfoBean.getData().getCoinCount() + "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                MyToast.myToast(MainActivity.this, "获取积分失败,请检查网络是否可用");
            }
        });
    }

    /**
     * 退出登录
     */
    private void Exit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("确认退出登录?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, null, "正在退出，请稍等...");
                OkHttpRequest.get(RequestURL.exit(), new MyCall() {
                    @Override
                    public void success(String json) {
                        try {
                            Gson gson = new Gson();
                            StateBean stateBean = gson.fromJson(json, StateBean.class);
                            if (stateBean.getErrorCode() == 0) {
                                progressDialog.dismiss();
                                sp.remove("cookie");
                                sp.remove("password");
                                loginLayout.setVisibility(View.VISIBLE);
                                successful_layout.setVisibility(View.GONE);
                                menu.findItem(R.id.exit).setVisible(false);
                                MyToast.myToast(MainActivity.this, "已退出登录");
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                MyToast.myToast(MainActivity.this, "退出失败,请检查网络是否可用");
                            }
                        } catch (Exception e) {
                            progressDialog.dismiss();
                            MyToast.myToast(MainActivity.this, "退出失败,请检查网络是否可用");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(IOException e) {
                        progressDialog.dismiss();
                        MyToast.myToast(MainActivity.this, "退出失败,请检查网络是否可用");
                    }
                });
            }
        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**
     * 上传头像
     */
    private void uploadThePicture() {
        if (!sp.getSharedPreference("cookie", "").toString().equals("")) {
            @SuppressLint("InflateParams")
            View layout = getLayoutInflater().inflate(R.layout.dialog_select_photo, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(layout).create();
            dialog.show();
            TextView takePhotoTV = layout.findViewById(R.id.photograph);
            TextView choosePhotoTV = layout.findViewById(R.id.photo);
            TextView cancelTV = layout.findViewById(R.id.cancel);
            // 拍照
            takePhotoTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  6.0之后动态申请权限 摄像头调取权限,SD卡写入权限
                    //判断是否拥有权限，true则动态申请
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 6);
                    } else {
                        try {
                            //有权限,去打开摄像头
                            takePhoto();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    dialog.dismiss();
                }
            });
            // 相册选择
            choosePhotoTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 6.0之后动态申请权限 SD卡写入权限
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7);
                    } else {
                        //打开系统默认的相册
                        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picture, 2);
                    }
                    dialog.dismiss();
                }
            });
            // 取消
            cancelTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } else {
            MyToast.myToast(MainActivity.this, "请先登录");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    /**
     * startActivityForResult执行后的回调方法，接收返回的图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode != Activity.RESULT_CANCELED) {
            String state = Environment.getExternalStorageState();
            if (!state.equals(Environment.MEDIA_MOUNTED)) return;
            // 把原图显示到界面上
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic/" + "UserIcon.png").asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                    headPortrait.setImageBitmap(bitmap);//显示图片到imgView上
                    StringAndBitmap.bitmapToString(MainActivity.this, bitmap);
                    MyToast.myToast(MainActivity.this, "上传成功");
                }
            });
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && null != data) {
            try {
                Uri selectedImage = data.getData(); //获取路径
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(selectedImage).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                        headPortrait.setImageBitmap(bitmap);//显示图片到imgView上
                        StringAndBitmap.bitmapToString(MainActivity.this, bitmap);
                        MyToast.myToast(MainActivity.this, "上传成功");
                    }
                });
            } catch (Exception e) {
                MyToast.myToast(MainActivity.this, "上传失败，请重新上传");
            }
        }
    }

    /**
     * 打开摄像头
     *
     * @throws IOException
     */
    private void takePhoto() throws IOException {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 获取文件
        File file = createFileIfNeed("UserIcon.png");
        //拍照后原图回存入此路径下
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(file);
        } else {
            // 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
            // 并且这样可以解决MIUI系统上拍照返回size为0的情况
            uri = FileProvider.getUriForFile(MainActivity.this, "com.example.wanandroid.ui.me.fileprovider", file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 1);
    }

    /**
     * 在sd卡中创建一保存图片（原图和缩略图共用的）文件夹
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private File createFileIfNeed(String fileName) throws IOException {
        String fileA = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic";
        File fileJA = new File(fileA);
        if (!fileJA.exists()) {
            fileJA.mkdirs();
        }
        File file = new File(fileA, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 申请权限回调方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 6) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    takePhoto();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MyToast.myToast(MainActivity.this, "系统权限拒绝了你的请求");
            }
        }

        if (requestCode == 7) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 2);
            } else {
                MyToast.myToast(MainActivity.this, "系统权限拒绝了你的请求");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 点击两次退出APP
     * 利用系统秒数
     */
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_EXIT > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            MyToast.myToast(MainActivity.this, "再点击一次退出应用");
            mBackPressed = System.currentTimeMillis();
        }
    }
}