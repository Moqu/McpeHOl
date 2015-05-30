package com.mclauncher.peonlinebox.mcmultiplayer.util;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by luoweiyi on 15/5/27.
 */
public abstract class NetCallBack extends TextHttpResponseHandler {

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onSuccess(int i, Header[] headers, String s) {
        myOnSuccess(s);
    }

    @Override
    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
        myOnFailure(throwable);
    }

    public abstract void myOnSuccess(String str);
    public abstract void myOnFailure(Throwable throwable);
}
