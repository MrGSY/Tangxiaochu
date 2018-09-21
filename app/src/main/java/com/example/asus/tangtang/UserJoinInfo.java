package com.example.asus.tangtang;

public class UserJoinInfo {
    //临时存储用户注册信息
    private static String UserPassword;
    private static boolean UserGender;
    private static String UserBirth;
    private static String UserWeight;
    private static String UserHeight;

    public static void setUserPassword(String userPassword){
        UserJoinInfo.UserPassword=userPassword;
    }

    public static void setUserGender(boolean userGender){
        UserJoinInfo.UserGender=userGender;
        //男为true，女为false
    }

    public static void setUserBirth(String userBirth){
        UserJoinInfo.UserBirth=userBirth;
    }

    public static void setUserWeight(String userWeight){
        UserJoinInfo.UserWeight=userWeight;
    }

    public static void setUserHeight(String userHeight){
        UserJoinInfo.UserHeight=userHeight;
    }

    public static String getUserPassword() {
        return UserPassword;
    }

    public static boolean isUserGender() {
        return UserGender;
    }

    public static String getUserWeight() {
        return UserWeight;
    }

    public static String getUserBirth() {
        return UserBirth;
    }

    public static String getUserHeight() {
        return UserHeight;
    }

}

