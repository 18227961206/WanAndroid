package com.example.wanandroid.ui.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Response;

public interface LoginMyCall {
    void success(String json, Response response, HttpUrl url);
    void failure(IOException e);
}
