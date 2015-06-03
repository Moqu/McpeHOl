package com.mclauncher.peonlinebox.mcmultiplayer.util;

import android.widget.TextView;

public class AuthCode {

    public static void authCodes(final TextView tvCode){
        String url = "http://192.168.1.103/tools/Code.php";
        RequestUtils.clientGet(url, new NetCallBack() {
            @Override
            public void myOnSuccess(String str) {
                tvCode.setText(str);
            }
            @Override
            public void myOnFailure(Throwable throwable) {
                tvCode.setText("点击重试!");
            }
        });
    }
}
