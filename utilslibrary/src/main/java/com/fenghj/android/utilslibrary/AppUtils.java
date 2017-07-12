package com.fenghj.android.utilslibrary;

import android.content.Intent;
import android.text.TextUtils;

/**
 * App相关工具类
 * <p>Created by Fenghj on 2017/7/7.</p>
 */

public class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取App包名
     *
     * @return App包名
     */
    public static String getAppPackageName() {
        return UtilsInit.getContext().getPackageName();
    }

    /**
     * 判断App是否安装
     *
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(String packageName) {
        return !TextUtils.isEmpty(packageName) && getLaunchAppIntent(packageName) != null;
    }

    /**
     * 打开App
     *
     * @param packageName 包名
     */
    public static void launchApp(String packageName) {
        if (TextUtils.isEmpty(packageName)) return;
        UtilsInit.getContext().startActivity(getLaunchAppIntent(packageName));
    }

    /**
     * 获取打开App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(String packageName) {
        return UtilsInit.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
    }
}
