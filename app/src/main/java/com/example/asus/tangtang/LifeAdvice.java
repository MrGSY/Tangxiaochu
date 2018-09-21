package com.example.asus.tangtang;

import java.util.ArrayList;
import java.util.List;

public class LifeAdvice {

    private double height;          //输入身高/m
    private double weight;          //输入体重/kg
    private double waistline;           //输入腰围/cm

    private boolean labor;  //输入劳动强度
    private boolean sport;  //输入每天运动量
    private boolean milk;           //输入每天奶类摄入量
    private boolean vegetable;           //输入每天蔬菜摄入量
    private boolean drink;  //输入含糖饮料情况
    private boolean pastry;  //输入糕点
    private boolean aftermeal;  //输入餐后血糖
    private boolean porridge;  //输入粥
    private boolean drug;  //输入药
    private boolean fruit;  //输入水果
    private boolean mainfood;  //输入主食
    private boolean egg;            //输入每周鸡蛋摄入量
    private boolean animal;  //输入动物内脏
    private boolean potato;  //输入淀粉类蔬菜
    private boolean frequency;  //输入用餐次数

    private int side;  //输入并发症

    private String gender;           //输入性别

    private boolean hypoglycemia;   //输入低血糖
    private boolean mealtime;  //输入用餐时间
    private boolean exercise;  //输入剧烈运动
    private boolean insulin;  //输入胰岛素
    private boolean meals;  //输入加餐
    private boolean liquor;  //输入饮酒
    private boolean extradrug;  //输入额外用药
    private boolean elsedrug;  //输入其他用药

    private boolean diagestion;  //输入不易消化
    private boolean toolate;  //输入检测过迟
    private boolean toomuch;  //输入胰岛素剂量过大；
    private boolean before;  //输入检测前运动

    //输入用户名，自动查询数据库加载加载身高体重性别
    /*
    public LifeAdvice(String userName) {
        try {

        }
        catch (Exception e) {
        }

    }*/


    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWaistline(double waistline) { this.waistline = waistline; }

    public void setLabor(boolean labor) { this.labor = labor; }

    public void setSport(boolean sport) { this.sport = sport; }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public void setVegetable(boolean vegetable) {
        this.vegetable = vegetable;
    }

    public void setDrink(boolean drink) { this.drink = drink; }

    public void setPastry(boolean pastry) { this.pastry = pastry; }

    public void setAftermeal(boolean aftermeal) { this.aftermeal = aftermeal; }

    public void setPorridge(boolean porridge) { this.porridge = porridge; }

    public void setDrug(boolean drug) { this.drug = drug; }

    public void setFruit(boolean fruit) { this.fruit = fruit; }

    public void setMainfood(boolean mainfood) { this.mainfood = mainfood; }

    public void setEgg(boolean egg) { this.egg = egg; }

    public void setAnimal(boolean animal) { this.animal = animal; }

    public void setPotato(boolean potato) { this.potato = potato; }

    public void setFrequency(boolean frequency) { this.frequency = frequency; }

    public void setSide(int side) { this.side = side; }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHypoglycemia(boolean hypoglycemia) { this.hypoglycemia = hypoglycemia; }

    public void setMealtime(boolean mealtime) { this.mealtime = mealtime; }

    public void setExercise(boolean exercise) { this.exercise = exercise; }

    public void setInsulin(boolean insulin) { this.insulin = insulin; }

    public void setMeals(boolean meals) { this.meals = meals; }

    public void setLiquor(boolean liquor) { this.liquor = liquor; }

    public void setExtradrug(boolean extradrug) { this.extradrug = extradrug; }

    public void setElsedrug(boolean elsedrug) { this.elsedrug = elsedrug; }

    public void setDiagestion(boolean diagestion) { this.diagestion = diagestion; }

    public void setToolate(boolean toolate) { this.toolate = toolate; }

    public void setToomuch(boolean toomuch) { this.toomuch = toomuch; }

    public void setBefore(boolean before) { this.before = before; }

    //根据信息获取所有建议
    public List<String> AllAdvice() {
        double BMI= weight*10000 /(height * height);
        List<String> advice = new ArrayList<String>();
        if((gender.equals("M") && waistline>90) || (gender.equals("F") && waistline>85)) advice.add("        请注意控制腰围，适量运动，预防腹型肥胖。");
        if (BMI>23.9) advice.add("        请注意合理规划饮食，适量运动，控制合理体型。");
        else if (BMI<18.5) advice.add("        请注意合理规划饮食，预防营养不良。");

        if(labor || sport) advice.add("        请根据您实际的日常能量消耗情况自行调整主食用量，能量消耗较少时，请减少主食的摄入，或者增加运动量，保持规律运动改善血糖情况。");
        if(milk) advice.add("        建议保证每日300克液态奶或相当量奶制品的摄入；重视大豆类及其制品的摄入；零食加餐可选择少许坚果。");
        if(vegetable) advice.add("        建议增加新鲜蔬菜摄入量以降低膳食血糖指数，餐餐有深色蔬菜。");
        if(drink) advice.add("        糖尿病患者可选用的饮用水有白开水、青钱柳树复合茶、冻干桑叶等，不宜饮用含糖饮料，需要警惕含有添加剂的奶制品等日常饮品。");
        if(pastry) advice.add("        糖尿病人忌食白糖、红糖、葡糖糖及糖制甜食，如果糖、糕点、果酱、蜂蜜、蜜饯、冰淇淋等，患者在生活中一定要注意，切忌使用。");
        if(aftermeal && !porridge && !drug) advice.add("        您餐后血糖异常，可能需要调整用药，请尽快前往医院就诊，咨询医生意见，修改治疗方案。");
        if(porridge) advice.add("        请尽量减少食用较为松软的食物，食物烹饪时间越长，更易消化，也更易升高血糖。");
        if(drug) advice.add("        请严格遵循医嘱用药，不可擅自更改用药次数和用药时间，请勿私自服用医嘱外的药品或药用性食物。");
        if(fruit) advice.add("        如果想要食用水果，请避免高糖水果（如西瓜等），而且不建议饭后立即食用水果，否则会使血糖升高，增加胰岛负担；食用水果后，需要适当减少主食的用量，并且将水果分成若干份，分次食用。");
        if(mainfood) advice.add("        建议选择低GI食物，全谷物、杂豆类应占主食摄入量的三份之一，粗粮和复合碳水化合物都是较好的选择，如玉米、燕麦、高粱、小米等。");
        if(egg) advice.add("        建议每周不超过4个鸡蛋、或每两天一个鸡蛋，不弃蛋黄。");
        if(animal) advice.add("        请尽量减少富含胆固醇的食物，包括蛋黄和脑、心、肺、肝等动物内脏，胆固醇会降低人体耐糖量，饮食尽量少油。");
        if(potato) advice.add("        如果您有食用红薯、土豆、山药、芋艿、藕等淀粉类蔬菜的习惯，请以淀粉类蔬菜替代主食，不要再额外食用主食。");
        if(frequency) advice.add("        糖尿病人的血糖不能被充分利用，因此会经常感到饥饿，建议采用少量多餐的策略，将一天的饮食用量均衡分配，规律饮食。");
        if(side==2) advice.add("        请减少您的主食用量，合理用药，以达到稳定血糖的目的。");
        if(side==3) advice.add("        请尽量减少富含胆固醇的食物，避免高油食物，日常饮食低油低盐。");
        if(side==4) advice.add("        请减少主食在您饮食中的比例，坚持日常锻炼，避免吸烟喝酒。");
        if(side==5) advice.add("        请减少蛋白质用量，并以优质蛋白为主，限制主食中的植物蛋白，比如豆类等。");
        if(side==6) advice.add("        请减少主食和脂肪的摄入量，适当增加蛋白质的摄入，增加体育锻炼，定期检测血糖和血压情况。");
        if(side==7) advice.add("        请减少主食和脂肪的摄入量，适当增加蛋白质的摄入，养成日常规律运动的习惯。");

        if(hypoglycemia) advice.add("        出现经常性的低血糖症，建议向医生咨询，调整用药。建议随身携带糖果，以防万一。");
        if(mealtime) advice.add("        主食可以提供人体活动的能量，也是最经济的人体营养来源，少吃或不吃，可能导致脂肪和蛋白质超标，而总能量却不够；建议规律饮食，按照营养师要求食用定量食物。");
        if(exercise) advice.add("        体力劳动或运动量增加时也应注意适当增加主食或加餐，否则易导致低血糖症，严重者会危及生命。");
        if(insulin && meals) advice.add("        胰岛素治疗者，应注意酌情在上午9～10点，下午3～4点或睡前加餐，少量多餐，每日可用餐5-6次，均衡饮食，防止发生低血糖。");
        if(liquor) advice.add("        酒精会增加低血糖风险，并且抑制中枢对低血糖的反应，掩盖低血糖症状，不建议糖尿病患者饮酒。");
        if(extradrug) advice.add("        请严格遵循医嘱用药，不可擅自更改用药次数和用药时间，更应避免额外服用其他降血糖用品。");
        if(elsedrug) advice.add("        许多药物或药用性食物都具有一定的降血糖功能，或是能够与患者正在服用的降血糖药物联合作用，比如用于解热止痛的阿司匹林；建议糖尿病患者服用其它药物前咨询医师，确认无风险再妥善服用。");

        if(diagestion) advice.add("        空腹血糖一般指禁食8-12小时后的血糖，即清晨空腹状态下的血糖，晚餐时间太晚会导致空腹时间不足8个小时，导致测得空腹血糖过高。建议您每日按时用餐，维持相对固定的用餐时间，以便管理自己的血糖情况。");
        if(toolate) advice.add("        测空腹血糖必须在上午8:00之前完成。如果超过8:00，虽然您仍处于空腹状态，但是由于生物钟的影响，升糖激素在8:00以后已逐渐增高，即使不吃饭，血糖也会随之上升，故这种情况下您检测的空腹血糖值会偏高。");
        if(toomuch) advice.add("        晚间胰岛素剂量过大，会导致出现休克现象，也会使得清晨空腹血糖异常升高。建议患者一定要严格遵循医嘱，控制自己的胰岛素使用量，才能更好地管理自己的身体健康。");
        if(before) advice.add("        医生要求测空腹血糖是在不进行晨练的情况下，因为运动后血糖一般会下降。建议您在测定空腹血糖之前不进行晨练，而将晨练时间放在测定之后。");

        if(advice.isEmpty()) advice.add("        您的生活习惯良好，血糖异常可能由用药方案不合理引起；如果再次发生血糖异常的情况，请立刻就诊咨询。");
        advice.add("        定期接受营养（医）师的个体化营养指导、频率至少每年四次！") ;
        return advice;
    }

}
