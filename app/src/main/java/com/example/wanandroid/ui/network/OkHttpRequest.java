package com.example.wanandroid.ui.network;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpRequest {

    /**
     * GET
     *
     * @param url
     * @param mycall
     */
    public static void get(String url, MyCall mycall) {
        Request request = new Request.Builder().get().url(url).build();
        NetWorkRequest.request(request, mycall);
    }

    /**
     * POST
     *
     * @param url
     * @param mycall
     */
    public static void post(String url, MyCall mycall) {
        RequestBody requestBody = new FormBody.Builder().build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        NetWorkRequest.request(request, mycall);
    }

    /**
     * POST
     * 登录时调用
     *
     * @param url
     * @param loginMyCall
     */
    public static void theLogin(String url, LoginMyCall loginMyCall) {
        RequestBody requestBody = new FormBody.Builder().build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        TheLogin.theLogin(request, loginMyCall);
    }

    /**
     * GET
     * 需要登录访问
     *
     * @param url
     * @param mycall
     */
    public static void loginRequiredOperationGet(String url, String cookie, MyCall mycall) {
        Request request = new Request.Builder().get().header("Cookie", cookie).url(url).build();
        NetWorkRequest.request(request, mycall);
    }


    /**
     * POST
     * 需要登录访问
     *
     * @param url
     * @param mycall
     */
    public static void loginRequiredOperationPost(String url, String cookie, MyCall mycall) {
        RequestBody requestBody = new FormBody.Builder().build();
        Request request = new Request.Builder().post(requestBody).header("Cookie", cookie).url(url).build();
        NetWorkRequest.request(request, mycall);
    }

}
