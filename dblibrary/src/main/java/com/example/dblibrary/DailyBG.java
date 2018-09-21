package com.example.dblibrary;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DailyBG extends LitePalSupport {

    private int year;
    private int month;
    private int date;
    private String dateInfo;
    public static final int MAX_BG_TYPE = 8;
    private double BG1=0,BG2=0,BG3=0,BG4=0,BG5=0,BG6=0,BG7=0,BG8=0;

    //构造函数
    private DailyBG(double BGSendIn, int year, int month, int date, int sendInType) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.dateInfo = getDateInfo(year, month, date);
        setBG(BGSendIn, sendInType);
    }

    public DailyBG(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.dateInfo = getDateInfo(year, month, date);
    }

    //更新血糖信息，自动加载年月日
    public static String updateBGInfo(double BGSendIn, int sendInType) {
        int year, month ,date;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        date = calendar.get(Calendar.DATE);
        DailyBG dailyBG;
        return updateBGInfo(BGSendIn, year, month, date, sendInType);
    }


    //更新血糖信息，手动加载年月日
    public static String updateBGInfo(double BGSendIn, int year, int month, int date, int type) {
        DailyBG dailyBG;
        try {
            dailyBG = DailyBG.getDailyBG(year, month, date);
            dailyBG.setBG(BGSendIn, type);
        }
        catch (Exception e) {
            dailyBG = new DailyBG(BGSendIn, year, month, date, type);
        }
        dailyBG.save();
        return BGAdvice.query(BGSendIn, type, false);
    }

    //将血糖类型与内部血糖代码对应，输入中文返回代码
    public static int toType(String stringType) {
        int intType = 0;
        switch (stringType) {
            case "空腹":
                intType = 1;
                break;
            case "早餐后":
                intType = 2;
                break;
            case "午餐前":
                intType = 3;
                break;
            case "午餐后":
                intType = 4;
                break;
            case "晚餐前":
                intType = 5;
                break;
            case "晚餐后":
                intType = 6;
                break;
            case "睡前":
                intType = 7;
                break;
                default:
                    intType = 8;
        }
        return intType;
    }

    //根据sendInType存储对应类型血糖值
    private void setBG(double BGSendIn, int type) {
        switch (type) {
            case 1:
                BG1 = BGSendIn;
                break;
            case 2:
                BG2 = BGSendIn;
                break;
            case 3:
                BG3 = BGSendIn;
                break;
            case 4:
                BG4 = BGSendIn;
                break;
            case 5:
                BG5 = BGSendIn;
                break;
            case 6:
                BG6 = BGSendIn;
                break;
            case 7:
                BG7 = BGSendIn;
                break;
            default:
                BG8 = BGSendIn;
        }
    }

    //返回对应类型血糖信息
    public double getBG(int type) {
        double ret=0;
        switch (type) {
            case 1:
                ret = BG1;
                break;
            case 2:
                ret = BG2;
                break;
            case 3:
                ret = BG3;
                break;
            case 4:
                ret = BG4;
                break;
            case 5:
                ret = BG5;
                break;
            case 6:
                ret = BG6;
                break;
            case 7:
                ret = BG7;
                break;
            default:
                ret = BG8;
        }
        return ret;
    }

    //根据日期查询数据库中的血糖信息，无信息记录则抛出异常
    public static DailyBG getDailyBG(int year, int month, int date) {
        String DateInfo = getDateInfo(year, month, date);
        List<DailyBG> dailyBGS =
                LitePal.where("dateInfo=?",DateInfo).find(DailyBG.class);
        if(dailyBGS.isEmpty()) dailyBGS.add(new DailyBG(year, month, date));
        return dailyBGS.get(0);
    }

    //获取所有已有血糖信息
    public static List<DailyBG> getAllInfo() {
        return LitePal.findAll(DailyBG.class);
    }

    //根据年月日生成数据标签
    public static String getDateInfo(int year, int month, int date) {
        int a = date + month*100 + year*10000;
        return Integer.toString(a);
    }

    public String getDateInfo() {
        return dateInfo;
    }



    //返回对应日期对应类型的血糖信息,无对应日期存储则抛出异常
    public static double getBGByDate(int year, int month, int date, int type) throws Exception {
        DailyBG dailyBG;
        try {
            dailyBG = DailyBG.getDailyBG(year, month, date);
            return dailyBG.getBG(type);
        }
        catch (Exception e) {
            Log.d("DailyBG","查无此日血糖记录"+"("+ DailyBG.getDateInfo(year, month, date)+")");
            throw new Exception();
        }
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    //返回最近七天的血糖数据信息
    public static List<DailyBG> weekList() {
        int year, month ,date;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        date = calendar.get(Calendar.DATE);
        String today = DailyBG.getDateInfo(year, month, date);
        List<DailyBG> weekList = LitePal.order("dateInfo desc").limit(7).find(DailyBG.class);
        Collections.reverse(weekList);
        return weekList;
    }

    //返回本月血糖信息，未存入的天数则返回数值为0的血糖信息
    public static List<DailyBG> monthList() {
        int year, month ,date;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        List<DailyBG> monthList = new ArrayList<DailyBG>();
        for(int i=1; i<=31; i++) {
            DailyBG dailyBG;
            try {
                dailyBG = DailyBG.getDailyBG(year,month,i);
            }
            catch (Exception e) {
                dailyBG = new DailyBG(year, month, i);
            }
            monthList.add(dailyBG);
        }
        return monthList;
    }

    //返回最近一天的血糖信息
    public static DailyBG latestBG() {
        DailyBG latest = LitePal.order("dateInfo desc").findFirst(DailyBG.class);
        if(latest == null) latest = new DailyBG(0,0,0);
        return latest;
    }

}
