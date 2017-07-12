package com.fenghj.android.utilslibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Utils initialize, can get ApplicationContext.
 * <br>Created by Fenghj on 2017/7/1.
 */

public class UtilsInit {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    private UtilsInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        UtilsInit.sContext = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (sContext != null) return sContext;
        throw new NullPointerException("u should init first");
    }
}
