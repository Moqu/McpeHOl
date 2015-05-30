package com.mclauncher.peonlinebox.mcmultiplayer.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Created by luoweiyi on 15/5/27.
 */
public class RequestUtils {

    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void clientGet(String url, RequestParams params, NetCallBack cb) {
        client.get(url, params, cb);
    }

    public static void clientGet(String url, NetCallBack cb) {
        client.get(url, cb);
    }

    public static void clientPost(String url, RequestParams params, NetCallBack cb) {
        client.post(url, params, cb);
    }
}
