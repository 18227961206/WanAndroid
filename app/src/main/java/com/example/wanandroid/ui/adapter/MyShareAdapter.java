package com.example.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.bean.MyShareBean;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyShareAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyShareBean.DataBean.ShareArticlesBean.DatasBean> dataList;
    private TextView fresh;
    private TextView shareUser;
    private ImageView collectionView;
    private ImageView delete;
    private TextView title;
    private TextView chapterName;
    private TextView niceDate;

    private Animation animation;
    private Map<Integer, Boolean> isFrist;
    private static SharedPreferencesCookieInfo collection;
    private static List<Integer> collectionList = new ArrayList<Integer>();
    private String cookie;


    @SuppressLint("UseSparseArrays")
    public MyShareAdapter(Context context, List<MyShareBean.DataBean.ShareArticlesBean.DatasBean> dataList) {
        this.mContext = context;
        this.dataList = dataList;
        // listView item动画
        animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top);
        isFrist = new HashMap<Integer, Boolean>();
        // 获取cookie
        cookie = GetCookie.getCookie(mContext);
        // 获取收藏
        collection = new SharedPreferencesCookieInfo(mContext, "collection");
        getCollectionList();
    }

    public static void theRefresh() {
        getCollectionList();
    }

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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_my_share, null);
        } else {
            view = convertView;
        }

        fresh = (TextView) view.findViewById(R.id.fresh);
        shareUser = (TextView) view.findViewById(R.id.shareUser);
        collectionView = (ImageView) view.findViewById(R.id.collection);
        delete = (ImageView) view.findViewById(R.id.delete);
        title = (TextView) view.findViewById(R.id.title);
        chapterName = (TextView) view.findViewById(R.id.chapterName);
        niceDate = (TextView) view.findViewById(R.id.niceDate);

        // 是否为最新
        if (dataList.get(position).isFresh()) {
            fresh.setVisibility(View.VISIBLE);
        } else {
            fresh.setVisibility(View.GONE);
        }
        // 没有作者则为分享人
        if (dataList.get(position).getAuthor().equals("")) {
            shareUser.setText(dataList.get(position).getShareUser());
        } else {
            shareUser.setText(dataList.get(position).getAuthor());
        }
        title.setText(dataList.get(position).getTitle());
        niceDate.setText(dataList.get(position).getNiceDate());
        chapterName.setText(dataList.get(position).getSuperChapterName() + " / " + dataList.get(position).getChapterName());

        // 如果是第一次加载该view，则使用动画
        if (isFrist.get(position) == null || isFrist.get(position)) {
            view.startAnimation(animation);
            isFrist.put(position, false);
        }

        // 是否已经收藏
        for (Integer temp : collectionList) {
            if (dataList.get(position).getId() == temp && !cookie.equals("")) {
                collectionView.setImageResource(R.drawable.ic_praise_selected);
                break;
            } else {
                collectionView.setImageResource(R.drawable.ic_praise);
            }
        }

        // 删除
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(mContext, null, "正在删除，请稍等...");
                OkHttpRequest.loginRequiredOperationPost(RequestURL.myShareDelete(dataList.get(position).getId()),
                        GetCookie.getCookie(mContext), new MyCall() {
                            @Override
                            public void success(String json) {
                                try {
                                    Gson gson = new Gson();
                                    StateBean stateBean = gson.fromJson(json, StateBean.class);
                                    if (stateBean.getErrorCode() == 0) {
                                        progressDialog.dismiss();
                                        dataList.remove(position);
                                        notifyDataSetChanged();
                                        MyToast.myToast(mContext, "已删除");
                                    } else {
                                        progressDialog.dismiss();
                                        MyToast.myToast(mContext, stateBean.getErrorMsg());
                                    }
                                } catch (Exception e) {
                                    progressDialog.dismiss();
                                    MyToast.myToast(mContext, "删除失败,请检查网络是否可用");
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failure(IOException e) {
                                progressDialog.dismiss();
                                MyToast.myToast(mContext, "删除失败,请检查网络是否可用");
                            }
                        });
            }
        });

        // 收藏点击事件
        collectionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collection(position);
            }
        });
        return view;
    }

    // 获取所有的收藏
    private static void getCollectionList() {
        collectionList.clear();
        Map<String, ?> stringMap = collection.getAll();
        for (Object value : stringMap.values()) {
            collectionList.add((Integer) value);
        }
    }

    // 收藏与取消收藏
    private void Collection(int position) {
        if (GetCookie.getCookie(mContext).equals("")) {
            MyToast.myToast(mContext, "请先登录");
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
        } else {
            boolean state = true;
            for (Integer temp : collectionList) {
                if (dataList.get(position).getId() == temp) {
                    state = false;
                    break;
                }
            }
            if (state) {
                // 收藏
                CollectionOperation(dataList.get(position).getId(), position, "collection");
            } else {
                // 取消收藏
                CollectionOperation(dataList.get(position).getId(), position, "cancel");
            }
        }
    }

    /**
     * 收藏与取消收藏
     *
     * @param id
     * @param position
     * @param state
     */
    private void CollectionOperation(int id, final int position, final String state) {
        String url;
        if (state.equals("cancel")) {
            // 取消收藏
            url = RequestURL.cancelTheCollection(id);
        } else {
            // 收藏
            url = RequestURL.collectionStationArticles(id);
        }
        OkHttpRequest.loginRequiredOperationPost(url, GetCookie.getCookie(mContext), new MyCall() {
            @Override
            public void success(String json) {
                try {
                    Gson gson = new Gson();
                    StateBean stateBean = gson.fromJson(json, StateBean.class);
                    if (stateBean.getErrorCode() == 0) {
                        if (state.equals("cancel")) {
                            collection.remove(dataList.get(position).getId() + "");
                            MyToast.myToast(mContext, "已取消收藏");
                        } else {
                            collection.setSharedPreference(dataList.get(position).getId() + "", dataList.get(position).getId());
                            MyToast.myToast(mContext, "收藏成功");
                        }
                        getCollectionList();
                        notifyDataSetChanged();
                    } else {
                        State(state);
                    }
                } catch (Exception e) {
                    State(state);
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(IOException e) {
                State(state);
            }
        });
    }

    // 提示成功与失败
    private void State(String state) {
        if (state.equals("cancel")) {
            MyToast.myToast(mContext, "取消收藏失败,请检查网络是否可用");
        } else {
            MyToast.myToast(mContext, "收藏失败,请检查网络是否可用");
        }
    }
}
