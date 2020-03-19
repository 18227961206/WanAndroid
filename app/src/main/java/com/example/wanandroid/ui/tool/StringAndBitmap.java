package com.example.wanandroid.ui.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: StringAndBitmap
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:36
 * @Description: 头像图片存储和读取
 * @version: 1.1.5
 */

public class StringAndBitmap {
    /**
     * Base64字符串转换成图片
     *
     * @param context
     * @return
     */
    public static Bitmap stringToBitmap(Context context) {
        Bitmap bitmap = null;
        String string = new SharedPreferencesCookieInfo(context, "HeadPortrait").getSharedPreference("headPortrait", "").toString();
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 图片转换成base64字符串 并存储到SharedPreference中
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static void bitmapToString(Context context, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        new SharedPreferencesCookieInfo(context, "HeadPortrait").
                setSharedPreference("headPortrait", Base64.encodeToString(imgBytes, Base64.DEFAULT));
    }
}
