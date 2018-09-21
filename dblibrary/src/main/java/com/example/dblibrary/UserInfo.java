package com.example.dblibrary;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class UserInfo extends LitePalSupport {
    private String name;
    private String gender;
    private int birth;
    private double weight;
    private double hight;
    private int type;
    private int time;
    public static final String GENDER_MAIL = "M";
    public static final String GENDER_FEMAIL = "F";
    public static final int TYPE_T1DM = 1;
    public static final int TYPE_T2DM = 2;
    public static final int TYPE_GDM = 3;
    public static final int TYPE_OTHER = 4;

    private UserInfo(String name) {
        this.name = name;
    }

    //查询用户名是否为空
    public static boolean isUserEmpty (String userName) {
        return LitePal.where("name=?",userName).find(UserInfo.class).isEmpty();
    }

    //注册
    public static UserInfo register(String userName, String password) throws Exception {
        UserInfo user;
        //Log.d("Register",Boolean.toString(LitePal.where("name=?").find(UserInfo.class).isEmpty()));
        try {
            if( !isUserEmpty(userName) ) {
                Log.d("Register","User all ready exist");
                throw new Exception();
            }
            MD5Util.SaveMD5pswd(userName, password);
            user = new UserInfo(userName);
            user.save();
        }
        catch (Exception e) {
            throw e;
        }
        return user;
    }

    //根据userName和password获取用户对象
    public static UserInfo getUser (String userName, String password) throws Exception {
        if(!MD5Util.VerifyPassword(userName, password)) {
            Log.d("Load", "Password wrong");
            throw new Exception();
        }
        try {
            return getUser(userName);
        }
        catch (Exception e) {
            throw e;
        }
    }

    //根据用户名获取用户对象，方便调试而设定，之后删除
    public static UserInfo getUser (String userName) throws Exception {
        UserInfo user;
        user = LitePal.where("name=?",userName).findFirst(UserInfo.class);
        if(user==null) throw new Exception();
        return user;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.save();
    }

    public void setType(int type) {
        this.type = type;
        this.save();
    }

    public void setTime(int time) {
        this.time = time;
        this.save();
    }

    public void setGender(String gender) {
        this.gender = gender;
        this.save();
    }

    public void setBirth(int birth) {
        this.birth = birth;
        this.save();
    }

    public String getGender() {
        return gender;
    }

    public int getType() {
        return type;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getBirth() {
        return birth;
    }

    public double getWeight() {
        return weight;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }
}
