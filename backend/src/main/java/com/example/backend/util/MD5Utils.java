package com.example.backend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            int i;
            StringBuilder stringBuffer = new StringBuilder("");
            for (byte b : digest) {
                i = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            // 32位加密
            return stringBuffer.toString();
            // 16位加密
//            return stringBuffer.toString().substring(8,24);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        System.out.println(code("123456"));
    }
}
