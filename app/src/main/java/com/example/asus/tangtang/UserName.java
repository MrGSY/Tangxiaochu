package com.example.asus.tangtang;

public class UserName {
    //存储用户名
    private static String username_saved;
    public static void setUsername_saved(String username_saved){
        UserName.username_saved=username_saved;
    }
    public static String getUsername_saved(){
        return username_saved;
    }
}
