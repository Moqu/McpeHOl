package com.mclauncher.peonlinebox.mcmultiplayer.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by luoweiyi on 15/5/25.
 */
public class ToastUtils {
    public static void toast(Context context,String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
