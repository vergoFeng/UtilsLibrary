package com.fenghj.android.utilslibrary;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * SD卡相关工具类
 *
 * <p>Created by Fenghj on 2017/7/17.</p>
 */

public class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取应用专属缓存目录<br>
     * android 4.4及以上系统不需要申请SD卡读写权限<br>
     * 因此也不用考虑6.0系统动态申请SD卡读写权限问题，切随应用被卸载后自动清空 不会污染用户存储空间
     *
     * @param type 文件夹类型 如果为空则返回 /storage/emulated/0/Android/data/app_package_name/cache
     *             否则返回对应类型的文件夹如Environment.DIRECTORY_PICTURES 对应的文件夹为 .../data/app_package_name/files/Pictures
     *             {@link Environment#DIRECTORY_MUSIC},
     *             {@link Environment#DIRECTORY_PODCASTS},
     *             {@link Environment#DIRECTORY_RINGTONES},
     *             {@link Environment#DIRECTORY_ALARMS},
     *             {@link Environment#DIRECTORY_NOTIFICATIONS},
     *             {@link Environment#DIRECTORY_PICTURES}, or
     *             {@link Environment#DIRECTORY_MOVIES}.or 自定义文件夹名称
     * @return 缓存目录文件夹 或 null（无SD卡或SD卡挂载失败）
     */
    public static File getCacheDirectory(String type) {
        File appCacheDir = null;
        if (isSDCardEnable()) {
            if (TextUtils.isEmpty(type)) {
                appCacheDir = UtilsInit.getContext().getExternalCacheDir();
            } else {
                appCacheDir = UtilsInit.getContext().getExternalFilesDir(type);
            }
            if (appCacheDir == null) {// 有些手机需要通过自定义目录
                appCacheDir = new File(Environment.getExternalStorageDirectory(),
                        "Android/data/" + UtilsInit.getContext().getPackageName() + "/cache/" + type);
            }
        }
        return appCacheDir;
    }

    /**
     * 获取应用专属缓存目录<br>
     *
     * @return null：不存在SD卡
     */
    public static String getCachePath(String type) {
        File cacheDirectory = getCacheDirectory(type);
        return cacheDirectory != null ? cacheDirectory.getPath() : null;
    }

    /**
     * 获取SD卡的根目录
     *
     * @return null：不存在SD卡
     */
    public static File getStorageDirectory() {
        return isSDCardEnable() ? Environment.getExternalStorageDirectory() : null;
    }

    /**
     * 获取SD卡的根路径
     *
     * @return null：不存在SD卡
     */
    public static String getStoragePath() {
        File rootDirectory = getStorageDirectory();
        return rootDirectory != null ? rootDirectory.getPath() : null;
    }
}
