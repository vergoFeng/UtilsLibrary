package com.fenghj.android.utilslibrary;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕相关工具类<br/>
 * <p>Created by Fenghj on 2017/7/1.</p>
 */

public class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return int 屏幕宽px
     */
    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) UtilsInit.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        }
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return int 屏幕高px
     */
    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) UtilsInit.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        }
        return dm.heightPixels;
    }
}
