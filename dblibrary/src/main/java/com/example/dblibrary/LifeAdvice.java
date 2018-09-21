package com.example.dblibrary;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LifeAdvice {
    private double yaowei;           //输入腰围/cm
    private double hight;          //输入身高/m
    private double weight;          //输入体重/kg
    private boolean zaliang;         //输入是经常食用杂粮
    private boolean sport;  //输入每天运动量
    private boolean vegetable;           //输入每天蔬菜摄入量
    private boolean milk;           //输入每天奶类摄入量
    private boolean egg;            //输入每周鸡蛋摄入量
    private String gender;

    //输入用户名，自动查询数据库加载加载身高体重性别
    public LifeAdvice(String userName) {
        try {
            UserInfo user = LitePal.where("name=?",userName).findFirst(UserInfo.class);
            hight = user.getHight();
            weight = user.getWeight();
            gender = user.getGender();
        }
        catch (Exception e) {
        }

    }

    public void setZaliang(boolean zaliang) {
        this.zaliang = zaliang;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setYaowei(double yaowei) {
        this.yaowei = yaowei;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    public void setVegetable(boolean vegetable) {
        this.vegetable = vegetable;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //根据信息获取所有建议
    public List<String> AllAdvice() {
        double BMI= weight /(hight * hight);
        List<String> advice = new ArrayList<String>();
        if((gender=="M"&&yaowei>90) || (gender=="F"&&yaowei>85)) advice.add("注意控制腰围，预防腹型肥胖.");
        if (BMI>23.9) advice.add("注意合理饮食，控制合理体型.");
        else if (BMI<18.5) advice.add("注意合理饮食，预防营养不良.");
        if (sport) advice.add("保持规律运动，以有氧运动为主.");
        if(!zaliang) advice.add("建议选择低GI食物，全谷物、杂豆类应占主食摄入量的三份之一.");
        if (vegetable) advice.add("建议增加新鲜蔬菜摄入量以降低膳食血糖指数，餐餐有深色蔬菜.");
        if (egg) advice.add("建议每周不超过4个鸡蛋、或每两天一个鸡蛋，不弃蛋黄.");
        if (milk) advice.add("建议保证每日300克液态奶或相当量奶制品的摄入；重视大豆类及其制品的摄入；零食加餐可选择少许坚果.");
        if(advice.isEmpty()) advice.add("您生活习惯良好，请继续保持.");
        advice.add("定期接受营养（医）师的个体化营养指导、频率至少每年四次!") ;
        return advice;
    }

}
