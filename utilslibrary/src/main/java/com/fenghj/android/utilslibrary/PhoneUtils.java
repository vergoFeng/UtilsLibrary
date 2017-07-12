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
    @SuppressLint("HardwareIds")
    public static String getPhoneIMEI() {
        TelephonyManager tm = (TelephonyManager) UtilsInit.getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
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
        TelephonyManager tm = (TelephonyManager) UtilsInit.getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm != null ? tm.getSimOperator() : null;
        if (operator == null) return null;
        switch (operator) {
            case "46000":
            case "46002":
            case "46007":
                return "2";
            case "46001":
                return "3";
            case "46003":
                return "1";
            default:
                return "4";
        }
    }
}
