package com.fenghj.android.utilslibrary;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密解密相关的工具类
 * <p>Created by Fenghj on 2017/7/14.</p>
 */

public class EncryptUtils {
    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD5ToString(final String data) {
        return bytes2HexString(encryptMD5(data.getBytes()));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(final byte[] data) {
        return hashTemplate(data, "MD5");
    }

    ///////////////////////////////////////////////////////////////////////////
    // AES加密相关
    ///////////////////////////////////////////////////////////////////////////
    /**
     * AES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String AES_Transformation = "AES/CBC/PKCS5Padding";
    private static final String AES_Algorithm = "AES";

    /**
     * AES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return String Base64加密后
     */
    public static String encryptAES2Base64(final String data, final String key, final String iv) {
        byte[] bytes = encryptAES(data, key, iv);
        if(bytes != null) return Base64.encodeToString(bytes, Base64.DEFAULT);
        return null;
    }

    /**
     * AES加密后转为16进制
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 16进制密文
     */
    public static String encryptAES2HexString(final String data, final String key, final String iv) {
        return bytes2HexString(encryptAES(data, key, iv));
    }

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 密文
     */
    public static byte[] encryptAES(final String data, final String key, final String iv) {
        return aesTemplate(data.getBytes(), key.getBytes(), iv.getBytes(), true);
    }

    /**
     * AES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 明文
     */
    public static String decryptBase64AES(final String data, final String key, final String iv) {
        if(data == null || "".equals(data)) return null;
        return new String(decryptAES(Base64.decode(data, Base64.DEFAULT), key, iv));
    }

    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 明文
     */
    public static String decryptHexStringAES(final String data, final String key, final String iv) {
        byte[] bytes = decryptAES(hexString2Bytes(data), key, iv);
        if(bytes == null || bytes.length == 0) return null;
        return new String(bytes);
    }

    /**
     * AES解密
     *
     * @param data 密文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 明文
     */
    public static byte[] decryptAES(final byte[] data, final String key, final String iv) {
        return aesTemplate(data, key.getBytes(), iv.getBytes(), false);
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    private static byte[] hexString2Bytes(String hexString) {
        if (hexString == null || "".equals(hexString)) return null;
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }

    private static int hex2Dec(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * hash加密模板
     *
     * @param data      数据
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES加密模板
     *
     * @param data      数据
     * @param key       秘钥
     * @param iv        向量
     * @param isEncrypt {@code true}: 加密 {@code false}: 解密
     * @return 密文或者明文
     */
    public static byte[] aesTemplate(final byte[] data, final byte[] key, final byte[] iv, final boolean isEncrypt) {
        if (data == null || data.length == 0 || key == null || key.length == 0 || iv == null || iv.length == 0)
            return null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, AES_Algorithm);
            Cipher cipher = Cipher.getInstance(AES_Transformation);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
