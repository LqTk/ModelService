package com.social.service.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    private static final String crypt[] = {"~","`","1","！","a","@","2","b","#",
            "3","c","$","4","D","%","5","e","^","6","f","&","7","G","*","8","H"};

    public static String byteArrayToEncrptyString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (byte item:bytes){
            sb.append(byteToEncryptString(item));
        }
        return sb.toString();
    }

    private static String byteToEncryptString(byte b){
        int n = b;
        if (n<0){
            n += 256;
        }
        int d1 = n/crypt.length;
        int d2 = n%crypt.length;
        return crypt[d1] + crypt[d2];
    }

    private static String encode(String origin, String charset){
        String encryptString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charset || "".equals(charset)){
                encryptString = byteArrayToEncrptyString(md.digest(origin.getBytes()));
            }else {
                encryptString = byteArrayToEncrptyString(md.digest(origin.getBytes(charset)));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptString.toUpperCase();
    }

    public static String encoderUTF8(String origin){
        return encode(origin,"utf-8");
    }
}
