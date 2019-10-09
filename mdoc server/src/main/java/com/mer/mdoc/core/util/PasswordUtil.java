package com.mer.mdoc.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Random;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: 1.0
 * @title
 */
public class PasswordUtil {

    private static final String ALGORITHM = "PBEWithMD5AndDES";//加密算法

    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 1000;

    /**
     * 加密
     * @param plaintext 明文
     * @param password 密码
     * @param salt 密码盐
     * @return
     */
    public static String encrypt(String plaintext, String password, String salt) {

        Key key = getPBEKey(password);
        byte[] encipheredData = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            //update-begin-author:sccott date:20180815 for:中文作为用户名时，加密的密码windows和linux会得到不同的结果 gitee/issues/IZUD7
            encipheredData = cipher.doFinal(plaintext.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHexString(encipheredData);
    }

    /**
     * 解密
     * @param cipherText 待解密的字符串
     * @param password 密码
     * @param salt 密码盐
     * @return 解密后的明文
     */
    public static String decrypt(String cipherText, String password, String salt) {
        Key key = getPBEKey(password);
        byte[] passDec = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt.getBytes(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            passDec = cipher.doFinal(hexStringToBytes(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(passDec);
    }

    /**
     * 将十六进制字符串转换为字节数组
     * @param hexString 十六进制字符串
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param src 字节数组
     * @return
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据PBE密码生成一把密钥
     * @param password
     * @return
     */
    private static Key getPBEKey(String password) {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 设置PBE密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            // 生成密钥
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 生成随机密码盐
     * @param len 长度
     * @return
     */
    public static String genSalt(int len) {
        String md = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuffer salt = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            salt.append(md.charAt(random.nextInt(md.length())));
        }
        return salt.toString();
    }

}
