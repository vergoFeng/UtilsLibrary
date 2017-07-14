package com.fenghj.android.utilslibrary;

import android.content.Intent;
import android.net.Uri;

/**
 * 意图相关工具类
 * <p>Created by Fenghj on 2017/7/13.</p>
 */

public class IntentUtils {
    private IntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取跳至拨号界面意图
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getDialIntent(final String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取拨打电话意图
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getCallIntent(final String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至发送短信界面的意图
     *
     * @param phoneNumber 接收号码
     */
    public static Intent getSendSmsIntent(final String phoneNumber) {
        return getSendSmsIntent(phoneNumber, "");
    }

    /**
     * 获取跳至发送短信界面的意图
     *
     * @param phoneNumber 接收号码
     * @param content     短信内容
     */
    public static Intent getSendSmsIntent(final String phoneNumber, final String content) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至发邮件的意图
     *
     * @param mailAdress 接收邮箱地址
     */
    public static Intent getSendMailIntent(final String mailAdress) {
        Uri uri = Uri.parse("mailto:"+mailAdress);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
