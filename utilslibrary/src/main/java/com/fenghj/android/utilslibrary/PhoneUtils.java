package com.fenghj.android.utilslibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 手机相关工具类
 * <p>Created by Fenghj on 2017/6/8.</p>
 */

public class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 获取手机IMEI码
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getPhoneIMEI() {
        TelephonyManager tm = (TelephonyManager) UtilsInit.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : "";
    }

    /**
     * 获取Sim卡运营商名称
     * <p>中国移动、如中国联通、中国电信</p>
     *
     * @return 移动网络运营商名称
     *          <br>{@code "中国电信"}</br>
     *          <br>{@code "中国移动"}</br>
     *          <br>{@code "中国联通"}</br>
     *          <br>{@code "未知"}</br>
     */
    public static String getSimOperatorByMnc() {
        TelephonyManager tm = (TelephonyManager) UtilsInit.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm != null ? tm.getSimOperator() : null;
        if (operator == null) return "未知";
        @SuppressLint("MissingPermission") String imsi = tm.getSubscriberId();
        switch (operator) {
            case "46000":
            case "46002":
            case "46007":
                return "中国移动";
            case "46001":
            case "46006":
                return "中国联通";
            case "46003":
            case "46005":
            case "46011":
                return "中国电信";
            default:
                return "未知";
        }
    }

    /**
     * 获取Sim卡运营商名称
     * <p>中国移动、如中国联通、中国电信</p>
     *
     * @return 移动网络运营商名称
     *          <br>{@code "1"：中国电信}</br>
     *          <br>{@code "2"：中国移动}</br>
     *          <br>{@code "3"：中国联通}</br>
     *          <br>{@code "4"：未知}</br>
     */
    public static String getSimOperatorCode() {
        String operatorName = getSimOperatorByMnc();
        switch (operatorName) {
            case "中国电信":
                return "1";
            case "中国移动":
                return "2";
            case "中国联通":
                return "3";
            case "未知":
                return "4";
            default:
                return "4";
        }
    }
}
