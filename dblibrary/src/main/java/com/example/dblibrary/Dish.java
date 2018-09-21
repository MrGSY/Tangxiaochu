package com.example.dblibrary;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Dish extends LitePalSupport {

    private int id;
    private String name;
    private String component;
    private String advice;
    private String URL;
    private String image;
    private int energy;
    private int carbohydrate;
    private int fat;
    private int protein;
    private int ForBreakfast;
    private boolean meat;
    private int cooldown;
    private static final int COOLDOWN_TIMES = 6;
    private static final int DISHES_NUMBER_BREAKFAST = 2;
    private static final int DISHES_NUMBER_DINNER = 3;
    public final static int TYPE_BREAKFAST = 1;
    public final static int TYPE_DINNER = 0;
    public final static int TYPE_LUNCH = 2;
    private int blacklist = 0;
    private String target;

    //按照名称寻找对应菜
    public static Dish findByName(String name) throws Exception {
        List<Dish> dishes = LitePal.where("name=?",name).find(Dish.class);
        if(dishes.isEmpty()) {
            throw new Exception();
        }
        return dishes.get(0);
    }

    //按照名称模糊查询
    public static List<Dish> findByName_multiple(String name) {
        return LitePal.where("name like ?","%"+name+"%").find(Dish.class);
    }

    public static List<Dish> findAllDishes() {
        return LitePal.findAll(Dish.class);
    }

    // 输入早/晚餐给出对应推荐菜
    public static List<Dish> getAdvicedMeal(int MealType) throws Exception {

        List<Dish> dishes;

        Calendar today = Calendar.getInstance();
        int year,month,date;
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH)+1;
        date = today.get(Calendar.DATE);
        String todayTarget = String.format("%d-%d-%d-%d",year,month,date,MealType);
        dishes = LitePal.where("target=?",todayTarget).find(Dish.class);
        if(dishes.size()>1) return dishes;

        dishes = LitePal.order("cooldown").find(Dish.class);
        int dishNumber = 0;
        int meatNumber = 0;
        switch (MealType) {
            case TYPE_BREAKFAST:
                dishNumber = DISHES_NUMBER_BREAKFAST;
                break;
            case TYPE_DINNER:
                dishNumber = DISHES_NUMBER_DINNER;
                break;
            case TYPE_LUNCH:
                dishNumber = DISHES_NUMBER_DINNER;
                MealType = 0;
                break;
                default:
                    dishNumber = 1;
        }
        //判断最近一天的血糖状况，由此决定推荐菜的荤素比例
        int measure = 0;
        DailyBG dailyBG = DailyBG.latestBG();
        for(int i=1;i<DailyBG.MAX_BG_TYPE;i++) {
            Log.d("measure","BG"+Integer.toString(i)+"  "+Double.toString(dailyBG.getBG(i)));
            if(dailyBG.getBG(i)==(double) 0) continue;
            else if(BGAdvice.query(dailyBG.getBG(i),i)) measure++;
        }
        if(measure<3 && MealType!=TYPE_BREAKFAST) meatNumber = 1;
        Log.d("measrue",Integer.toString(measure));

        List<Dish> dishesReturn = new ArrayList<>();
        for(Dish dish: dishes) {
            if(MealType!=dish.getForBreakfast() || dish.blacklist==1) {
                continue;
            }
            if(dish.cooldown >0) {
                dish.cooldown--;
                dish.save();
            }
            if(dish.isMeat() && meatNumber==0) continue;
            if(!dish.isMeat() && dishNumber==meatNumber) continue;
            dishesReturn.add(dish);
            dish.target = todayTarget;
            dish.cooldown = Dish.COOLDOWN_TIMES;
            dish.save();
            dishNumber--;
            if(dish.isMeat()) meatNumber--;
        }
        if(dishNumber!=0) throw new Exception();
        if(dishesReturn.isEmpty()) throw new Exception();
        return dishesReturn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdvice() {
        return advice;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public String getComponent() { return component; }

    public String getURL() { return URL; }

    public String getImage() { return image; }

    public int getForBreakfast() {
        return ForBreakfast;
    }

    public boolean isMeat() {
        return meat;
    }

    public void setBlacklist(int blacklist) {
        this.blacklist = blacklist;
        this.save();
    }

    public static List<Dish> getBlacklist() {
        List<Dish> dishes = LitePal.where("blacklist=?", "1").find(Dish.class);
        return dishes;
    }

    //输入菜名，将对应菜品加入黑名单
    public static void setBlacklistByName(String name, int isblacklist) {
        try {
            Dish dish = Dish.findByName(name);
            dish.setBlacklist(isblacklist);
        }
        catch (Exception e) {};
    }

    public int getCooldown() {
        return cooldown;
    }
}
