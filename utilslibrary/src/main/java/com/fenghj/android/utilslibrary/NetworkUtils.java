package com.fenghj.android.utilslibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络相关工具类
 * <p>Created by Fenghj on 2017/7/1.<p/>
 */

public class NetworkUtils {
    public static final String NETWORK_WIFI = "Wi-Fi";
    public static final String NETWORK_4G = "4g";
    public static final String NETWORK_3G = "3g";
    public static final String NETWORK_2G = "2g";
    public static final String NETWORK_UNKNOWN = "unknown";
    public static final String NETWORK_NONE = "none";

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) UtilsInit.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }
    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 判断wifi是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) UtilsInit.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取当前网络类型
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return 网络类型
     * <ul>
     * <li>{@link #NETWORK_WIFI   }</li>
     * <li>{@link #NETWORK_4G     }</li>
     * <li>{@link #NETWORK_3G     }</li>
     * <li>{@link #NETWORK_2G     }</li>
     * <li>{@link #NETWORK_UNKNOWN}</li>
     * <li>{@link #NETWORK_NONE   }</li>
     * </ul>
     */
    public static String getNetworkType() {
        ConnectivityManager cm = (ConnectivityManager) UtilsInit.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null) {
            String type = info.getTypeName();
            if(type.toLowerCase().equals("wifi")) {
                return NETWORK_WIFI;
            } else {
                if(type.toLowerCase().equals("mobile")) {
                    type = info.getSubtypeName();
                    if(type.toLowerCase().equals("gsm") || type.toLowerCase().equals("gprs")
                            || type.toLowerCase().equals("edge")) {
                        return NETWORK_2G;
                    } else if(type.toLowerCase().startsWith("cdma") || type.toLowerCase().equals("umts")
                            || type.toLowerCase().equals("1xrtt") || type.toLowerCase().equals("ehrpd")
                            || type.toLowerCase().equals("hsupa") || type.toLowerCase().equals("hsdpa")
                            || type.toLowerCase().equals("hspa")) {
                        return NETWORK_3G;
                    } else if(type.toLowerCase().equals("lte") || type.toLowerCase().equals("umb")
                            || type.toLowerCase().equals("hspa+")) {
                        return NETWORK_4G;
                    }
                }
                return NETWORK_UNKNOWN;
            }
        } else {
            return NETWORK_NONE;
        }
    }
    /**
     * 获取当前网络自定义标识类型
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return String 类型<br>
     *     {@code 1}: wifi<br>
     *     {@code 2}: 4G<br>
     *     {@code 3}: 3G<br>
     *     {@code 4}: 2G<br>
     *     {@code 5}: none<br>
     */
    public static String getNetTypeNum() {
        switch (getNetworkType()) {
            case NETWORK_WIFI:
                return "1";
            case NETWORK_4G:
                return "2";
            case NETWORK_3G:
                return "3";
            case NETWORK_2G:
                return "4";
            default:
                return "5";
        }
    }
}
