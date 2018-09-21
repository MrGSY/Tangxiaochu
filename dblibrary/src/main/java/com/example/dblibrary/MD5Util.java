package com.example.dblibrary;

import android.content.Context;
import android.content.SharedPreferences;

import org.litepal.LitePalApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encrypBy(String raw) {
        String md5Str = raw;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] encrypContext = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encrypContext.length; offset++) {
                i = encrypContext[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            md5Str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    //用SharedPreferences将得到的十六进制存在本地中
    public static void SaveMD5pswd(String username, String md5pswd) {
        Context context = LitePalApplication.getContext();
        SharedPreferences.Editor editor =
                context.getSharedPreferences("password", Context.MODE_PRIVATE).edit();
        editor.putString(username, md5pswd);
        editor.commit();
    }

    //用SharedPreferences验证用户名和密码
    public static Boolean VerifyPassword(String username, String pswd) {
        Context context = LitePalApplication.getContext();
        SharedPreferences pref =
                context.getSharedPreferences("password", Context.MODE_PRIVATE);
        String md5pswd = pref.getString(username,"none");
        if(md5pswd.equals("none")){
            return false;
        }
        if(md5pswd.equals(encrypBy(pswd))){
            return true;
        }
        else {
            return false;
        }
    }
}
