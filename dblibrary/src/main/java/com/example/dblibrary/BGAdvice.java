package com.example.dblibrary;

public class BGAdvice {
    public static final int TYPE_ZAOQIAN = 1;
    public static final int TYPE_ZAOHOU = 2;
    public static final int TYPE_WUQIAN = 3;
    public static final int TYPE_WUHOU = 4;
    public static final int TYPE_WANQIAN = 5;
    public static final int TYPE_WANHOU = 6;
    public static final int TYPE_SHUIQIAN = 7;

    public static String query(double BG, int type, boolean isYestday) {
        final String GOOD = "您的血糖情况正常，请继续保持!";
        final String COMMON = "您的血糖情况良好，请注意控制饮食和运动量!";
        final String BELOW = "您的血糖偏低，建议适当加餐补充糖分，多检测血糖，避免低血糖的发生!";
        final String HIGH = "您的血糖值偏高，建议严格按照食谱控制饮食，适当增加饭后运动，如有不适请向医生咨询!";
        final String LIANGTIAN = "您的血糖值持续偏高，请尽快前去就医，遵照医嘱执行!";
        String Suggest;
        Suggest = "";
        switch (type){
            case 1:
                if (BG>=4.4&&BG<=6.1)Suggest = GOOD;
                if (BG>6.1&&BG<=7.0)Suggest = COMMON;
                if (BG<4.4)Suggest = BELOW;
                if (BG>7.0){
                    if (isYestday)Suggest = LIANGTIAN;
                    else Suggest = HIGH;
                }
                break;

            case 7:
                if (BG>=5.6&&BG<=7.8)Suggest = GOOD;
                if (BG>4.4&&BG<=5.6)Suggest = COMMON;
                if (BG<5.6)Suggest = BELOW;
                if (BG>10.0){
                    if (isYestday)Suggest = LIANGTIAN;
                    else Suggest = HIGH;
                }
                break;

            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                if (BG>=4.4&&BG<=8.0)Suggest = GOOD;
                if (BG>8.0&&BG<=10.0)Suggest = COMMON;
                if (BG<4.4)Suggest = BELOW;
                if (BG>10.0){
                    if (isYestday)Suggest = LIANGTIAN;
                    else Suggest = HIGH;
                }
                break;
        }
        return Suggest;
    }
    public static int query_detail(double BG, int type) {
        int ret = 0;
        switch (type){
            case 1:
                if (BG<4.4) ret = -1;
                else if(BG>7.0) ret = 1;
                break;
            case 7:
                if (BG<4.4) ret = -1;
                else if(BG>7.8) ret = 1;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                if (BG<4.4) ret = -1;
                else if(BG>10.0) ret = 1;
                break;
        }
        return ret;
    }

    public static boolean query(double BG, int type) {
        if(query_detail(BG,type)==0) return false;
        else return true;
    }

}
