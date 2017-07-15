package com.fenghj.android.utilslibrary;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

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
        Uri uri = Uri.parse("mailto:" + mailAdress);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开相机拍照的意图（兼容7.0）
     * <p>需添加权限 :
     *     <br>{@code <uses-permission android:name="android.permission.CAMERA"/>}</br>
     *     <br>{@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</br>
     * </p>
     *
     * @param file 拍照保存图片的文件
     */
    public static Intent getCameraIntent(File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(UtilsInit.getContext().getPackageManager()) != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            } else {
                //兼容7.0调用相机
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                Uri uri = UtilsInit.getContext().getContentResolver()
                        .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        } else {
            Toast.makeText(UtilsInit.getContext(), "没有系统相机", Toast.LENGTH_SHORT).show();
        }
        return intent;
    }
}
