package com.example.wanandroid.ui.network;

import java.io.IOException;

public interface MyCall {
    public void success(String json);
    public void failure(IOException e);
}
