package com.example.wanandroid.ui.tool;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Collection {

    private SharedPreferencesCookieInfo collection;
    private List<Integer> collectionList = new ArrayList<Integer>();

    public Collection(Context context) {
        collection = new SharedPreferencesCookieInfo(context, "collection");
    }

    private List<Integer> getCollectionList(){
        collectionList.clear();
        Map<String, ?> stringMap = collection.getAll();
        for (Object value : stringMap.values()) {
            collectionList.add((Integer) value);
        }
        return collectionList;
    }
}
