package com.example.asus.tangtang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dblibrary.BGAdvice;
import com.example.dblibrary.DailyBG;
import com.example.dblibrary.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QAndAActivity extends AppCompatActivity {

    private LinearLayout test_layout;
    private Page the_page;
    //答案列表
    private ArrayList<Answer> the_answer_list;
    //问题列表
    private ArrayList<Question> the_question_list;
    //问题所在的View
    private View que_view;
    //答案所在的View
    private View ans_view;
    private LayoutInflater xInflater;
    private Page page;
    //下面这两个list是为了实现点击的时候改变图片，因为单选多选时情况不一样，为了方便控制
    //存每个问题下的imageview
    private ArrayList<ArrayList<ImageView>> imglist=new ArrayList<ArrayList<ImageView>>();
    //存每个答案的imageview
    private ArrayList<ImageView> imglist2;

    double height;          //输入身高/m
    double weight;          //输入体重/kg
    double waistline;           //输入腰围/cm

    boolean labor=false;  //输入劳动强度
    boolean sport=false;  //输入每天运动量
    boolean milk=false;           //输入每天奶类摄入量
    boolean vegetable=false;           //输入每天蔬菜摄入量
    boolean drink=false;  //输入含糖饮料情况
    boolean pastry=false;  //输入糕点
    boolean aftermeal=false;  //输入餐后血糖
    boolean porridge=false;  //输入粥
    boolean drug=false;  //输入药
    boolean fruit=false;  //输入水果
    boolean mainfood=false;  //输入主食
    boolean egg=false;            //输入每周鸡蛋摄入量
    boolean animal=false;  //输入动物内脏
    boolean potato=false;  //输入淀粉类蔬菜
    boolean frequency=false;  //输入用餐次数

    int side=0;  //输入并发症

    String gender;           //输入性别

    boolean hypoglycemia=false;   //输入低血糖
    boolean mealtime=false;  //输入用餐时间
    boolean exercise=false;  //输入剧烈运动
    boolean insulin=false;  //输入胰岛素
    boolean meals=false;  //输入加餐
    boolean liquor=false;  //输入饮酒
    boolean extradrug=false;  //输入额外用药
    boolean elsedrug=false;  //输入其他用药

    boolean diagestion=false;  //输入不易消化
    boolean toolate=false;  //输入检测过迟
    boolean toomuch=false;  //输入胰岛素剂量过大；
    boolean before=false;  //输入检测前运动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_and_a);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "QAndAActivity");

        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //假数据
        initDate();
        //提交按钮
        Button button=(Button)findViewById(R.id.submit);
        button.setOnClickListener((View.OnClickListener) new submitOnClickListener(page));

        ImageButton goback=(ImageButton)findViewById(R.id.goback_qanda);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initDate() {
        //高血糖
        // TODO Auto-generated method stub

        Answer answer_2a=new Answer();
        answer_2a.setAnswerId("answer_2a");
        answer_2a.setAnswer_content("是");
        answer_2a.setAns_state(0);
        Answer answer_2b=new Answer();
        answer_2b.setAnswerId("answer_2b");
        answer_2b.setAnswer_content("否");
        answer_2b.setAns_state(0);

        Answer answer_3a=new Answer();
        answer_3a.setAnswerId("answer_3a");
        answer_3a.setAnswer_content("是");
        answer_3a.setAns_state(0);
        Answer answer_3b=new Answer();
        answer_3b.setAnswerId("answer_3b");
        answer_3b.setAnswer_content("否");
        answer_3b.setAns_state(0);

        Answer answer_4a=new Answer();
        answer_4a.setAnswerId("answer_4a");
        answer_4a.setAnswer_content("是");
        answer_4a.setAns_state(0);
        Answer answer_4b=new Answer();
        answer_4b.setAnswerId("answer_4b");
        answer_4b.setAnswer_content("否");
        answer_4b.setAns_state(0);

        Answer answer_5a=new Answer();
        answer_5a.setAnswerId("answer_5a");
        answer_5a.setAnswer_content("重体力劳动");
        answer_5a.setAns_state(0);
        Answer answer_5b=new Answer();
        answer_5b.setAnswerId("answer_5b");
        answer_5b.setAnswer_content("中体力劳动");
        answer_5b.setAns_state(0);
        Answer answer_5c=new Answer();
        answer_5c.setAnswerId("answer_5c");
        answer_5c.setAnswer_content("轻体力劳动");
        answer_5c.setAns_state(0);
        Answer answer_5d=new Answer();
        answer_5d.setAnswerId("answer_5d");
        answer_5d.setAnswer_content("卧床");
        answer_5d.setAns_state(0);

        Answer answer_6a=new Answer();
        answer_6a.setAnswerId("answer_6a");
        answer_6a.setAnswer_content("是");
        answer_6a.setAns_state(0);
        Answer answer_6b=new Answer();
        answer_6b.setAnswerId("answer_6b");
        answer_6b.setAnswer_content("否");
        answer_6b.setAns_state(0);

        Answer answer_7a=new Answer();
        answer_7a.setAnswerId("answer_7a");
        answer_7a.setAnswer_content("是");
        answer_7a.setAns_state(0);
        Answer answer_7b=new Answer();
        answer_7b.setAnswerId("answer_7b");
        answer_7b.setAnswer_content("否");
        answer_7b.setAns_state(0);

        Answer answer_8a=new Answer();
        answer_8a.setAnswerId("answer_8a");
        answer_8a.setAnswer_content("是");
        answer_8a.setAns_state(0);
        Answer answer_8b=new Answer();
        answer_8b.setAnswerId("answer_8b");
        answer_8b.setAnswer_content("否");
        answer_8b.setAns_state(0);

        Answer answer_9a=new Answer();
        answer_9a.setAnswerId("answer_9a");
        answer_9a.setAnswer_content("是");
        answer_9a.setAns_state(0);
        Answer answer_9b=new Answer();
        answer_9b.setAnswerId("answer_9b");
        answer_9b.setAnswer_content("否");
        answer_9b.setAns_state(0);

        Answer answer_10a=new Answer();
        answer_10a.setAnswerId("answer_10a");
        answer_10a.setAnswer_content("是");
        answer_10a.setAns_state(0);
        Answer answer_10b=new Answer();
        answer_10b.setAnswerId("answer_10b");
        answer_10b.setAnswer_content("否");
        answer_10b.setAns_state(0);

        Answer answer_11a=new Answer();
        answer_11a.setAnswerId("answer_11a");
        answer_11a.setAnswer_content("是");
        answer_11a.setAns_state(0);
        Answer answer_11b=new Answer();
        answer_11b.setAnswerId("answer_11b");
        answer_11b.setAnswer_content("否");
        answer_11b.setAns_state(0);

        Answer answer_12a=new Answer();
        answer_12a.setAnswerId("answer_12a");
        answer_12a.setAnswer_content("是");
        answer_12a.setAns_state(0);
        Answer answer_12b=new Answer();
        answer_12b.setAnswerId("answer_12b");
        answer_12b.setAnswer_content("否");
        answer_12b.setAns_state(0);

        Answer answer_13a=new Answer();
        answer_13a.setAnswerId("answer_13a");
        answer_13a.setAnswer_content("是");
        answer_13a.setAns_state(0);
        Answer answer_13b=new Answer();
        answer_13b.setAnswerId("answer_13b");
        answer_13b.setAnswer_content("否");
        answer_13b.setAns_state(0);

        Answer answer_14a=new Answer();
        answer_14a.setAnswerId("answer_14a");
        answer_14a.setAnswer_content("是");
        answer_14a.setAns_state(0);
        Answer answer_14b=new Answer();
        answer_14b.setAnswerId("answer_14b");
        answer_14b.setAnswer_content("否");
        answer_14b.setAns_state(0);

        Answer answer_15a=new Answer();
        answer_15a.setAnswerId("answer_15a");
        answer_15a.setAnswer_content("精白米面");
        answer_15a.setAns_state(0);
        Answer answer_15b=new Answer();
        answer_15b.setAnswerId("answer_15b");
        answer_15b.setAnswer_content("豆类");
        answer_15b.setAns_state(0);
        Answer answer_15c=new Answer();
        answer_15c.setAnswerId("answer_15c");
        answer_15c.setAnswer_content("玉米");
        answer_15c.setAns_state(0);
        Answer answer_15d=new Answer();
        answer_15d.setAnswerId("answer_15d");
        answer_15d.setAnswer_content("高粱");
        answer_15d.setAns_state(0);
        Answer answer_15e=new Answer();
        answer_15e.setAnswerId("answer_15e");
        answer_15e.setAnswer_content("其他");
        answer_15e.setAns_state(0);

        Answer answer_16a=new Answer();
        answer_16a.setAnswerId("answer_16a");
        answer_16a.setAnswer_content("是");
        answer_16a.setAns_state(0);
        Answer answer_16b=new Answer();
        answer_16b.setAnswerId("answer_16b");
        answer_16b.setAnswer_content("否");
        answer_16b.setAns_state(0);

        Answer answer_17a=new Answer();
        answer_17a.setAnswerId("answer_17a");
        answer_17a.setAnswer_content("是");
        answer_17a.setAns_state(0);
        Answer answer_17b=new Answer();
        answer_17b.setAnswerId("answer_17b");
        answer_17b.setAnswer_content("否");
        answer_17b.setAns_state(0);

        Answer answer_18a=new Answer();
        answer_18a.setAnswerId("answer_18a");
        answer_18a.setAnswer_content("是");
        answer_18a.setAns_state(0);
        Answer answer_18b=new Answer();
        answer_18b.setAnswerId("answer_18b");
        answer_18b.setAnswer_content("否");
        answer_18b.setAns_state(0);

        Answer answer_19a=new Answer();
        answer_19a.setAnswerId("answer_19a");
        answer_19a.setAnswer_content("2次");
        answer_19a.setAns_state(0);
        Answer answer_19b=new Answer();
        answer_19b.setAnswerId("answer_19b");
        answer_19b.setAnswer_content("3次");
        answer_19b.setAns_state(0);
        Answer answer_19c=new Answer();
        answer_19c.setAnswerId("answer_19c");
        answer_19c.setAnswer_content("4次");
        answer_19c.setAns_state(0);
        Answer answer_19d=new Answer();
        answer_19d.setAnswerId("answer_19d");
        answer_19d.setAnswer_content("4次以上");
        answer_19d.setAns_state(0);

        Answer answer_20a=new Answer();
        answer_20a.setAnswerId("answer_20a");
        answer_20a.setAnswer_content("轻型糖尿病");
        answer_20a.setAns_state(0);
        Answer answer_20b=new Answer();
        answer_20b.setAnswerId("answer_20b");
        answer_20b.setAnswer_content("血糖尿糖均高");
        answer_20b.setAns_state(0);
        Answer answer_20c=new Answer();
        answer_20c.setAnswerId("answer_20c");
        answer_20c.setAnswer_content("合并高胆固醇血症");
        answer_20c.setAns_state(0);
        Answer answer_20d=new Answer();
        answer_20d.setAnswerId("answer_20d");
        answer_20d.setAnswer_content("合并高三酰甘油血症");
        answer_20d.setAns_state(0);
        Answer answer_20e=new Answer();
        answer_20e.setAnswerId("answer_20e");
        answer_20e.setAnswer_content("合并肾功能不全");
        answer_20e.setAns_state(0);
        Answer answer_20f=new Answer();
        answer_20f.setAnswerId("answer_20f");
        answer_20f.setAnswer_content("合并高血压");
        answer_20f.setAns_state(0);
        Answer answer_20g=new Answer();
        answer_20g.setAnswerId("answer_20g");
        answer_20g.setAnswer_content("合并多种并发症");
        answer_20g.setAns_state(0);

        //低血糖
        Answer answer_21a=new Answer();
        answer_21a.setAnswerId("answer_21a");
        answer_21a.setAnswer_content("是");
        answer_21a.setAns_state(0);
        Answer answer_21b=new Answer();
        answer_21b.setAnswerId("answer_21b");
        answer_21b.setAnswer_content("否");
        answer_21b.setAns_state(0);

        Answer answer_22a=new Answer();
        answer_22a.setAnswerId("answer_22a");
        answer_22a.setAnswer_content("是");
        answer_22a.setAns_state(0);
        Answer answer_22b=new Answer();
        answer_22b.setAnswerId("answer_22b");
        answer_22b.setAnswer_content("否");
        answer_22b.setAns_state(0);

        Answer answer_23a=new Answer();
        answer_23a.setAnswerId("answer_23a");
        answer_23a.setAnswer_content("是");
        answer_23a.setAns_state(0);
        Answer answer_23b=new Answer();
        answer_23b.setAnswerId("answer_23b");
        answer_23b.setAnswer_content("否");
        answer_23b.setAns_state(0);

        Answer answer_24a=new Answer();
        answer_24a.setAnswerId("answer_24a");
        answer_24a.setAnswer_content("是");
        answer_24a.setAns_state(0);
        Answer answer_24b=new Answer();
        answer_24b.setAnswerId("answer_24b");
        answer_24b.setAnswer_content("否");
        answer_24b.setAns_state(0);

        Answer answer_25a=new Answer();
        answer_25a.setAnswerId("answer_25a");
        answer_25a.setAnswer_content("是");
        answer_25a.setAns_state(0);
        Answer answer_25b=new Answer();
        answer_25b.setAnswerId("answer_25b");
        answer_25b.setAnswer_content("否");
        answer_25b.setAns_state(0);

        Answer answer_26a=new Answer();
        answer_26a.setAnswerId("answer_26a");
        answer_26a.setAnswer_content("是");
        answer_26a.setAns_state(0);
        Answer answer_26b=new Answer();
        answer_26b.setAnswerId("answer_26b");
        answer_26b.setAnswer_content("否");
        answer_26b.setAns_state(0);

        Answer answer_27a=new Answer();
        answer_27a.setAnswerId("answer_27a");
        answer_27a.setAnswer_content("是");
        answer_27a.setAns_state(0);
        Answer answer_27b=new Answer();
        answer_27b.setAnswerId("answer_27b");
        answer_27b.setAnswer_content("否");
        answer_27b.setAns_state(0);

        Answer answer_28a=new Answer();
        answer_28a.setAnswerId("answer_28a");
        answer_28a.setAnswer_content("是");
        answer_28a.setAns_state(0);
        Answer answer_28b=new Answer();
        answer_28b.setAnswerId("answer_28b");
        answer_28b.setAnswer_content("否");
        answer_28b.setAns_state(0);

        Answer answer_29a=new Answer();
        answer_29a.setAnswerId("answer_29a");
        answer_29a.setAnswer_content("是");
        answer_29a.setAns_state(0);
        Answer answer_29b=new Answer();
        answer_29b.setAnswerId("answer_29b");
        answer_29b.setAnswer_content("否");
        answer_29b.setAns_state(0);

        ArrayList<Answer> answers_2=new ArrayList<Answer>();
        answers_2.add(answer_2a);
        answers_2.add(answer_2b);

        ArrayList<Answer> answers_3=new ArrayList<Answer>();
        answers_3.add(answer_3a);
        answers_3.add(answer_3b);

        ArrayList<Answer> answers_4=new ArrayList<Answer>();
        answers_4.add(answer_4a);
        answers_4.add(answer_4b);

        ArrayList<Answer> answers_5=new ArrayList<Answer>();
        answers_5.add(answer_5a);
        answers_5.add(answer_5b);
        answers_5.add(answer_5c);
        answers_5.add(answer_5d);

        ArrayList<Answer> answers_6=new ArrayList<Answer>();
        answers_6.add(answer_6a);
        answers_6.add(answer_6b);

        ArrayList<Answer> answers_7=new ArrayList<Answer>();
        answers_7.add(answer_7a);
        answers_7.add(answer_7b);

        ArrayList<Answer> answers_8=new ArrayList<Answer>();
        answers_8.add(answer_8a);
        answers_8.add(answer_8b);

        ArrayList<Answer> answers_9=new ArrayList<Answer>();
        answers_9.add(answer_9a);
        answers_9.add(answer_9b);

        ArrayList<Answer> answers_10=new ArrayList<Answer>();
        answers_10.add(answer_10a);
        answers_10.add(answer_10b);

        ArrayList<Answer> answers_11=new ArrayList<Answer>();
        answers_11.add(answer_11a);
        answers_11.add(answer_11b);

        ArrayList<Answer> answers_12=new ArrayList<Answer>();
        answers_12.add(answer_12a);
        answers_12.add(answer_12b);

        ArrayList<Answer> answers_13=new ArrayList<Answer>();
        answers_13.add(answer_13a);
        answers_13.add(answer_13b);

        ArrayList<Answer> answers_14=new ArrayList<Answer>();
        answers_14.add(answer_14a);
        answers_14.add(answer_14b);

        ArrayList<Answer> answers_15=new ArrayList<Answer>();
        answers_15.add(answer_15a);
        answers_15.add(answer_15b);
        answers_15.add(answer_15c);
        answers_15.add(answer_15d);
        answers_15.add(answer_15e);

        ArrayList<Answer> answers_16=new ArrayList<Answer>();
        answers_16.add(answer_16a);
        answers_16.add(answer_16b);

        ArrayList<Answer> answers_17=new ArrayList<Answer>();
        answers_17.add(answer_17a);
        answers_17.add(answer_17b);

        ArrayList<Answer> answers_18=new ArrayList<Answer>();
        answers_18.add(answer_18a);
        answers_18.add(answer_18b);

        ArrayList<Answer> answers_19=new ArrayList<Answer>();
        answers_19.add(answer_19a);
        answers_19.add(answer_19b);
        answers_19.add(answer_19c);
        answers_19.add(answer_19d);

        ArrayList<Answer> answers_20=new ArrayList<Answer>();
        answers_20.add(answer_20a);
        answers_20.add(answer_20b);
        answers_20.add(answer_20c);
        answers_20.add(answer_20d);
        answers_20.add(answer_20e);
        answers_20.add(answer_20f);
        answers_20.add(answer_20g);

        ArrayList<Answer> answers_21=new ArrayList<Answer>();
        answers_21.add(answer_21a);
        answers_21.add(answer_21b);

        ArrayList<Answer> answers_22=new ArrayList<Answer>();
        answers_22.add(answer_22a);
        answers_22.add(answer_22b);

        ArrayList<Answer> answers_23=new ArrayList<Answer>();
        answers_23.add(answer_23a);
        answers_23.add(answer_23b);

        ArrayList<Answer> answers_24=new ArrayList<Answer>();
        answers_24.add(answer_24a);
        answers_24.add(answer_24b);

        ArrayList<Answer> answers_25=new ArrayList<Answer>();
        answers_25.add(answer_25a);
        answers_25.add(answer_25b);

        ArrayList<Answer> answers_26=new ArrayList<Answer>();
        answers_26.add(answer_26a);
        answers_26.add(answer_26b);

        ArrayList<Answer> answers_27=new ArrayList<Answer>();
        answers_27.add(answer_27a);
        answers_27.add(answer_27b);

        ArrayList<Answer> answers_28=new ArrayList<Answer>();
        answers_28.add(answer_28a);
        answers_28.add(answer_28b);

        ArrayList<Answer> answers_29=new ArrayList<Answer>();
        answers_29.add(answer_29a);
        answers_29.add(answer_29b);


        Question question_1=new Question();
        question_1.setQuestionId("1");
        question_1.setType("2");
        question_1.setContent("1、请输入您的腰围（cm）");
        question_1.setQue_input("");
        question_1.setQue_state(0);


        //高血糖
        Question question_2=new Question();
        question_2.setQuestionId("2");
        question_2.setType("0");
        question_2.setContent("、您是否昨晚食用了大量不好消化的食物，或者晚餐时间过晚？");
        question_2.setAnswers(answers_2);
        question_2.setQue_state(0);

        Question question_3=new Question();
        question_3.setQuestionId("3");
        question_3.setType("0");
        question_3.setContent("、您检测空腹血糖的时间是不是超过早晨8:00了？");
        question_3.setAnswers(answers_3);
        question_3.setQue_state(0);

        Question question_4=new Question();
        question_4.setQuestionId("4");
        question_4.setType("0");
        question_4.setContent("、您是否晚间胰岛素剂量过大？");
        question_4.setAnswers(answers_4);
        question_4.setQue_state(0);

        Question question_5=new Question();
        question_5.setQuestionId("5");
        question_5.setType("0");
        question_5.setContent("、请问您近期的劳动强度怎么样？");
        question_5.setAnswers(answers_5);
        question_5.setQue_state(0);

        Question question_6=new Question();
        question_6.setQuestionId("6");
        question_6.setType("0");
        question_6.setContent("、请问您近期是否有规律的体育锻炼（每周3次以上，每次20分钟以上）？");
        question_6.setAnswers(answers_6);
        question_6.setQue_state(0);

        Question question_7=new Question();
        question_7.setQuestionId("7");
        question_7.setType("0");
        question_7.setContent("、您是否每日摄入300g以上的液态奶（约一盒牛奶）？");
        question_7.setAnswers(answers_7);
        question_7.setQue_state(0);

        Question question_8=new Question();
        question_8.setQuestionId("8");
        question_8.setType("0");
        question_8.setContent("、您是否每日食用300g以上的绿色蔬菜（约可由双手捧起的量）？");
        question_8.setAnswers(answers_8);
        question_8.setQue_state(0);

        Question question_9=new Question();
        question_9.setQuestionId("9");
        question_9.setType("0");
        question_9.setContent("、近期是否饮用含糖量较高的饮料或含有添加剂的饮品（如可乐等碳酸饮料）？");
        question_9.setAnswers(answers_9);
        question_9.setQue_state(0);

        Question question_10=new Question();
        question_10.setQuestionId("10");
        question_10.setType("0");
        question_10.setContent("、最近是否曾食用糕点、果酱等高糖食物或是其他用料不清的食品？");
        question_10.setAnswers(answers_10);
        question_10.setQue_state(0);

        Question question_11=new Question();
        question_11.setQuestionId("11");
        question_11.setType("0");
        question_11.setContent("、您是否经常性的餐后血糖上升过快？");
        question_11.setAnswers(answers_11);
        question_11.setQue_state(0);

        Question question_12=new Question();
        question_12.setQuestionId("12");
        question_12.setType("0");
        question_12.setContent("、您近期是否食用较为松软的食物，如粥、糊等？");
        question_12.setAnswers(answers_12);
        question_12.setQue_state(0);

        Question question_13=new Question();
        question_13.setQuestionId("13");
        question_13.setType("0");
        question_13.setContent("、您是否严格遵循医嘱用药？");
        question_13.setAnswers(answers_13);
        question_13.setQue_state(0);

        Question question_14=new Question();
        question_14.setQuestionId("14");
        question_14.setType("0");
        question_14.setContent("、您是否经常食用水果？");
        question_14.setAnswers(answers_14);
        question_14.setQue_state(0);

        Question question_15=new Question();
        question_15.setQuestionId("15");
        question_15.setType("1");
        question_15.setContent("、请问您主要食用的主食有哪些？");
        question_15.setAnswers(answers_15);
        question_15.setQue_state(0);

        Question question_16=new Question();
        question_16.setQuestionId("16");
        question_16.setType("0");
        question_16.setContent("、您是否每周食用4个以上的鸡蛋？");
        question_16.setAnswers(answers_16);
        question_16.setQue_state(0);

        Question question_17=new Question();
        question_17.setQuestionId("17");
        question_17.setType("0");
        question_17.setContent("、您是否经常食用脑、心、肺、肝等动物内脏？");
        question_17.setAnswers(answers_17);
        question_17.setQue_state(0);

        Question question_18=new Question();
        question_18.setQuestionId("18");
        question_18.setType("0");
        question_18.setContent("、您是否经常食用土豆、红薯、山药等淀粉类蔬菜？");
        question_18.setAnswers(answers_18);
        question_18.setQue_state(0);

        Question question_19=new Question();
        question_19.setQuestionId("19");
        question_19.setType("0");
        question_19.setContent("、您每天的用餐次数？");
        question_19.setAnswers(answers_19);
        question_19.setQue_state(0);

        Question question_20=new Question();
        question_20.setQuestionId("20");
        question_20.setType("0");
        question_20.setContent("、您有糖尿病并发症吗？您属于下列哪种糖尿病分型？");
        question_20.setAnswers(answers_20);
        question_20.setQue_state(0);

        //低血糖
        Question question_21=new Question();
        question_21.setQuestionId("21");
        question_21.setType("0");
        question_21.setContent("2、您是否经常出现低血糖症（嘴巴麻痹、皮肤湿冷、胸部有颤动感觉、饥饿）？");
        question_21.setAnswers(answers_21);
        question_21.setQue_state(0);

        Question question_22=new Question();
        question_22.setQuestionId("22");
        question_22.setType("0");
        question_22.setContent("3、您是否未按时进食，或进食过少？");
        question_22.setAnswers(answers_22);
        question_22.setQue_state(0);

        Question question_23=new Question();
        question_23.setQuestionId("23");
        question_23.setType("0");
        question_23.setContent("4、最近是否进行过剧烈运动或增加劳动量，并且仍保持正常饮食？");
        question_23.setAnswers(answers_23);
        question_23.setQue_state(0);

        Question question_24=new Question();
        question_24.setQuestionId("24");
        question_24.setType("0");
        question_24.setContent("5、您是否正在进行注射胰岛素或口服降血糖药物治疗方案？");
        question_24.setAnswers(answers_24);
        question_24.setQue_state(0);

        Question question_25=new Question();
        question_25.setQuestionId("25");
        question_25.setType("0");
        question_25.setContent("6、您是否保持每日加餐的习惯（每日用餐5-6次）？");
        question_25.setAnswers(answers_25);
        question_25.setQue_state(0);

        Question question_26=new Question();
        question_26.setQuestionId("26");
        question_26.setType("0");
        question_26.setContent("7、您近期是否饮酒？");
        question_26.setAnswers(answers_26);
        question_26.setQue_state(0);

        Question question_27=new Question();
        question_27.setQuestionId("27");
        question_27.setType("0");
        question_27.setContent("8、您是否严格遵照医嘱用药？无额外服用任何降血糖用品？");
        question_27.setAnswers(answers_27);
        question_27.setQue_state(0);

        Question question_28=new Question();
        question_28.setQuestionId("28");
        question_28.setType("0");
        question_28.setContent("9、您是否服用作用于其他病症的药品或药用性食物？");
        question_28.setAnswers(answers_28);
        question_28.setQue_state(0);

        Question question_29=new Question();
        question_29.setQuestionId("29");
        question_29.setType("0");
        question_29.setContent("10、您测定空腹血糖前是否进行过晨练？");
        question_29.setAnswers(answers_29);
        question_29.setQue_state(0);


        //判断患者类型
        String username = UserName.getUsername_saved();
        UserInfo user;

        try {
            user = UserInfo.getUser(username);
            height=user.getHight();
            weight = user.getWeight();
            gender=user.getGender();

        }
        catch (Exception e) {
        }
        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
        double BG = intent.getDoubleExtra("level",0.0);
        int timing = DailyBG.toType(type);  //血糖异常的时间点
        int level = BGAdvice.query_detail(BG,timing);  //血糖高or低

        //所有问题的list
        ArrayList<Question> allquestions=new ArrayList<Question>();
        allquestions.add(question_5);
        allquestions.add(question_6);
        allquestions.add(question_7);
        allquestions.add(question_8);
        allquestions.add(question_9);
        allquestions.add(question_10);
        allquestions.add(question_13);
        allquestions.add(question_14);
        allquestions.add(question_16);
        allquestions.add(question_17);
        allquestions.add(question_18);
        allquestions.add(question_19);
        allquestions.add(question_20);
        if(timing==1){  //空腹
            allquestions.add(question_2);
            allquestions.add(question_3);
            allquestions.add(question_4);
        }else{
            allquestions.add(question_12);
            allquestions.add(question_15);
            allquestions.add(question_11);
        }

        //随机产生的一系列问题的list
        Random rand=new Random();
        if(timing==1 || timing==2 || timing==4 ||timing==6){
            for(int i=0;i<5;i++){
                int t=rand.nextInt(13-i);
                Question question_temp=allquestions.get(12-i);
                allquestions.set(12-i,allquestions.get(t));
                allquestions.set(t,question_temp);
            }
        }
        else{
            for(int i=0;i<8;i++){
                int t=rand.nextInt(16-i);
                Question question_temp=allquestions.get(15-i);
                allquestions.set(15-i,allquestions.get(t));
                allquestions.set(t,question_temp);
            }
        }

        ArrayList<Question> questions=new ArrayList<Question>();
        questions.add(question_1);
        //高血糖
        if(level==1){
            for(int i=0;i<8;i++){
                allquestions.get(15-i).setContent(String.valueOf(i+2)+allquestions.get(15-i).getContent());
                questions.add(allquestions.get(15-i));
            }
        }
        //低血糖
        else{
            questions.add(question_21);
            questions.add(question_22);
            questions.add(question_23);
            questions.add(question_24);
            questions.add(question_25);
            questions.add(question_26);
            questions.add(question_27);
            questions.add(question_28);
            if(timing==1) questions.add(question_29);  //空腹
        }

        page=new Page();
        page.setPageId("000");
        page.setStatus("0");
        page.setTitle("身体状况与饮食健康调查");
        page.setQuestions(questions);
        //加载布局
        initView(page);
    }
    private void initView(Page page) {
        // TODO Auto-generated method stub
        //这是要把问题的动态布局加入的布局
        test_layout=(LinearLayout)findViewById(R.id.lly_test);
        TextView page_txt=(TextView)findViewById(R.id.txt_title);
        page_txt.setText(page.getTitle());
        //获得问题即第二层的数据
        the_question_list=page.getQuestions();
        //根据第二层问题的多少，来动态加载布局
        for(int i=0;i<the_question_list.size();i++){

            if(the_question_list.get(i).getType().equals("2")){
                que_view=xInflater.inflate(R.layout.input_layout, null);
                TextView txt_que=(TextView)que_view.findViewById(R.id.txt_question_item);
                //这是第三层布局要加入的地方
                set(txt_que,the_question_list.get(i).getContent(),2);
                final EditText edit_que=(EditText) que_view.findViewById(R.id.lly_input);
                final int finalI = i;
                edit_que.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        String message=edit_que.getText().toString();
                        the_question_list.get(finalI).setQue_input(message);
                    }
                });
            }

            else{
                que_view=xInflater.inflate(R.layout.question_layout, null);
                TextView txt_que=(TextView)que_view.findViewById(R.id.txt_question_item);
                //这是第三层布局要加入的地方
                LinearLayout add_layout=(LinearLayout)que_view.findViewById(R.id.lly_answer);
                //判断单选-多选来实现后面是*号还是*多选，
                if(the_question_list.get(i).getType().equals("1")){
                    set(txt_que,the_question_list.get(i).getContent(),1);
                }else{
                    set(txt_que,the_question_list.get(i).getContent(),0);
                }
                //获得答案即第三层数据
                the_answer_list=the_question_list.get(i).getAnswers();
                imglist2=new ArrayList<ImageView>();
                for(int j=0;j<the_answer_list.size();j++){
                    ans_view=xInflater.inflate(R.layout.answer_layout, null);
                    TextView txt_ans=(TextView)ans_view.findViewById(R.id.txt_answer_item);
                    ImageView image=(ImageView)ans_view.findViewById(R.id.image);
                    View line_view=ans_view.findViewById(R.id.vw_line);
                    if(j==the_answer_list.size()-1){
                        //最后一条答案下面不要线是指布局的问题
                        line_view.setVisibility(View.GONE);
                    }
                    //判断单选多选加载不同选项图片
                    if(the_question_list.get(i).getType().equals("1")){
                        image.setBackgroundDrawable(getResources().getDrawable(R.drawable.multiselect_false));
                    }else{
                        image.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_false));
                    }
                    Log.e("---", "------"+image);
                    imglist2.add(image);
                    txt_ans.setText(the_answer_list.get(j).getAnswer_content());
                    LinearLayout lly_answer_size=(LinearLayout)ans_view.findViewById(R.id.lly_answer_size);
                    lly_answer_size.setOnClickListener((View.OnClickListener) new answerItemOnClickListener(i,j,the_answer_list,txt_ans));
                    add_layout.addView(ans_view);
                }
                imglist.add(imglist2);
            }


			/*for(int r=0; r<imglist2.size();r++){
				Log.e("---", "imglist2--------"+imglist2.get(r));
			}*/

            test_layout.addView(que_view);
        }
		/*for(int q=0;q<imglist.size();q++){
			for(int w=0;w<imglist.get(q).size();w++){
				Log.e("---", "共有------"+imglist.get(q).get(w));
			}
		}*/

    }
    private void set(TextView tv_test, String content,int type) {
        //为了加载问题后面的* 和*多选
        // TODO Auto-generated method stub
        String w;
        if(type==1){
            w = content+"*[多选题]";
        }else{
            w = content+"*";
        }

        int start = content.length();
        int end = w.length();
        Spannable word = new SpannableString(w);
        word.setSpan(new AbsoluteSizeSpan(25), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new StyleSpan(Typeface.BOLD), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv_test.setText(word);
    }

    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        private int j;
        private TextView txt;
        private ArrayList<Answer> the_answer_lists;
        public answerItemOnClickListener(int i,int j, ArrayList<Answer> the_answer_list,TextView text){
            this.i=i;
            this.j=j;
            this.the_answer_lists=the_answer_list;
            this.txt=text;

        }
        //实现点击选项后改变选中状态以及对应图片
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //判断当前问题是单选还是多选
			/*Log.e("------", "选择了-----第"+i+"题");
			for(int q=0;q<imglist.size();q++){
				for(int w=0;w<imglist.get(q).size();w++){
//					Log.e("---", "共有------"+imglist.get(q).get(w));
				}
			}
			Log.e("----", "点击了---"+imglist.get(i).get(j));*/

            if(the_question_list.get(i).getType().equals("1")){
                //多选
                if(the_answer_lists.get(j).getAns_state()==0){
                    //如果未被选中
                    txt.setTextColor(Color.parseColor("#EA5514"));
                    imglist.get(i-1).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.multiselect_true));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }else{
                    txt.setTextColor(Color.parseColor("#595757"));
                    imglist.get(i-1).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.multiselect_false));
                    the_answer_lists.get(j).setAns_state(0);
                    the_question_list.get(i).setQue_state(1);
                }
            }
            if(the_question_list.get(i).getType().equals("0")){
                //单选

                for(int z=0;z<the_answer_lists.size();z++){
                    the_answer_lists.get(z).setAns_state(0);
                    imglist.get(i-1).get(z).setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_false));
                }
                if(the_answer_lists.get(j).getAns_state()==0){
                    //如果当前未被选中
                    imglist.get(i-1).get(j).setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_true));
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }else{
                    //如果当前已被选中
                    the_answer_lists.get(j).setAns_state(1);
                    the_question_list.get(i).setQue_state(1);
                }

            }
            //判断当前选项是否选中

        }

    }

    class submitOnClickListener implements View.OnClickListener {
        private Page page;
        public submitOnClickListener(Page page){
            this.page=page;
        }
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            //判断是否答完题
            boolean isState=true;
            //最终要的json数组
            JSONArray jsonArray = new JSONArray();
            //点击提交的时候，先判断状态，如果有未答完的就提示，如果没有再把每条答案提交（包含问卷ID 问题ID 及答案ID）
            //注：不用管是否是一个问题的答案，就以答案的个数为准来提交上述格式的数据
            for(int i=0;i<the_question_list.size();i++){
                if(the_question_list.get(i).getType().equals("2")){
                    if(the_question_list.get(i).getQue_input().length()==0){
                        Toast.makeText(getApplicationContext(), "您第"+(i+1)+"题没有答完", Toast.LENGTH_LONG).show();
                        jsonArray=null;
                        isState=false;
                        break;
                    }else{
                        if(i==0){
                            waistline=Double.parseDouble(the_question_list.get(i).getQue_input());
                            Log.e("waistline", String.valueOf(waistline));
                        }

                    }
                }
                else{
                    the_answer_list=the_question_list.get(i).getAnswers();
                    //判断是否有题没答完
                    if(the_question_list.get(i).getQue_state()==0){
                        Toast.makeText(getApplicationContext(), "您第"+(i+1)+"题没有答完", Toast.LENGTH_LONG).show();
                        jsonArray=null;
                        isState=false;
                        break;
                    }else{
                        for(int j=0;j<the_answer_list.size();j++){
                            if(the_answer_list.get(j).getAns_state()==1){
                                JSONObject json = new JSONObject();
                                try {
                                    json.put("psychologicalId", page.getPageId());
                                    json.put("questionId", the_question_list.get(i).getQuestionId());
                                    json.put("optionId", the_answer_list.get(j).getAnswerId());

                                    //赋值
                                    if(the_question_list.get(i).getQuestionId()=="2")
                                        diagestion=(the_answer_list.get(j).getAnswerId()=="answer_2a");
                                    if(the_question_list.get(i).getQuestionId()=="3")
                                        toolate=(the_answer_list.get(j).getAnswerId()=="answer_3a");
                                    if(the_question_list.get(i).getQuestionId()=="4")
                                        toomuch=(the_answer_list.get(j).getAnswerId()=="answer_4a");
                                    if(the_question_list.get(i).getQuestionId()=="5")
                                        labor=(the_answer_list.get(j).getAnswerId()=="answer_5c")||(the_answer_list.get(j).getAnswerId()=="answer_5d");
                                    if(the_question_list.get(i).getQuestionId()=="6")
                                        sport=(the_answer_list.get(j).getAnswerId()=="answer_6b");
                                    if(the_question_list.get(i).getQuestionId()=="7")
                                        milk=(the_answer_list.get(j).getAnswerId()=="answer_7b");
                                    if(the_question_list.get(i).getQuestionId()=="8")
                                        vegetable=(the_answer_list.get(j).getAnswerId()=="answer_8b");
                                    if(the_question_list.get(i).getQuestionId()=="9")
                                        drink=(the_answer_list.get(j).getAnswerId()=="answer_9a");
                                    if(the_question_list.get(i).getQuestionId()=="10")
                                        pastry=(the_answer_list.get(j).getAnswerId()=="answer_10a");
                                    if(the_question_list.get(i).getQuestionId()=="11")
                                        aftermeal=(the_answer_list.get(j).getAnswerId()=="answer_11a");
                                    if(the_question_list.get(i).getQuestionId()=="12")
                                        porridge=(the_answer_list.get(j).getAnswerId()=="answer_12a");
                                    if(the_question_list.get(i).getQuestionId()=="13")
                                        drug=(the_answer_list.get(j).getAnswerId()=="answer_13b");
                                    if(the_question_list.get(i).getQuestionId()=="14")
                                        fruit=(the_answer_list.get(j).getAnswerId()=="answer_14a");
                                    if(the_question_list.get(i).getQuestionId()=="15")
                                        if((the_answer_list.get(j).getAnswerId()=="answer_15a")||(the_answer_list.get(j).getAnswerId()=="answer_15e"))
                                            mainfood=true;
                                    if(the_question_list.get(i).getQuestionId()=="16")
                                        egg=(the_answer_list.get(j).getAnswerId()=="answer_16a");
                                    if(the_question_list.get(i).getQuestionId()=="17")
                                        animal=(the_answer_list.get(j).getAnswerId()=="answer_17a");
                                    if(the_question_list.get(i).getQuestionId()=="18")
                                        potato=(the_answer_list.get(j).getAnswerId()=="answer_18a");
                                    if(the_question_list.get(i).getQuestionId()=="19")
                                        frequency=(the_answer_list.get(j).getAnswerId()=="answer_19a")||(the_answer_list.get(j).getAnswerId()=="answer_19b");
                                    if(the_question_list.get(i).getQuestionId()=="20"){
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20a") side=1;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20b") side=2;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20c") side=3;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20d") side=4;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20e") side=5;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20f") side=6;
                                        if(the_answer_list.get(j).getAnswerId()=="answer_20g") side=7;
                                    }

                                    if(the_question_list.get(i).getQuestionId()=="21")
                                        hypoglycemia=(the_answer_list.get(j).getAnswerId()=="answer_21a");
                                    if(the_question_list.get(i).getQuestionId()=="22")
                                        mealtime=(the_answer_list.get(j).getAnswerId()=="answer_22a");
                                    if(the_question_list.get(i).getQuestionId()=="23")
                                        exercise=(the_answer_list.get(j).getAnswerId()=="answer_23a");
                                    if(the_question_list.get(i).getQuestionId()=="24")
                                        insulin=(the_answer_list.get(j).getAnswerId()=="answer_24a");
                                    if(the_question_list.get(i).getQuestionId()=="25")
                                        meals=(the_answer_list.get(j).getAnswerId()=="answer_25b");
                                    if(the_question_list.get(i).getQuestionId()=="26")
                                        liquor=(the_answer_list.get(j).getAnswerId()=="answer_26a");
                                    if(the_question_list.get(i).getQuestionId()=="27")
                                        extradrug=(the_answer_list.get(j).getAnswerId()=="answer_27b");
                                    if(the_question_list.get(i).getQuestionId()=="28")
                                        elsedrug=(the_answer_list.get(j).getAnswerId()=="answer_28a");
                                    if(the_question_list.get(i).getQuestionId()=="29")
                                        before=(the_answer_list.get(j).getAnswerId()=="answer_29a");

                                    jsonArray.put(json);
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

            }
            if(isState){
                if(jsonArray.length()>0){
                    Log.e("result", jsonArray.toString());
                    for(int item=0;item<jsonArray.length();item++){
                        JSONObject job;
                        try {
                            job = jsonArray.getJSONObject(item);
                            Log.e("----", "pageId--------"+job.get("pageId"));
                            Log.e("----", "questionId--------"+job.get("questionId"));
                            Log.e("----", "answerId--------"+job.get("answerId"));
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }  // 遍历 jsonarray 数组，把每一个对象转成 json 对象

                    }

                }

                //LifeAdvice
                LifeAdvice advice = new LifeAdvice();
                advice.setHeight(height);
                advice.setWeight(weight);
                advice.setWaistline(waistline);
                advice.setDiagestion(diagestion);
                advice.setToolate(toolate);
                advice.setToomuch(toomuch);
                advice.setLabor(labor);
                advice.setSport(sport);
                advice.setMilk(milk);
                advice.setVegetable(vegetable);
                advice.setDrink(drink);
                advice.setPastry(pastry);
                advice.setAftermeal(aftermeal);
                advice.setPorridge(porridge);
                advice.setDrug(drug);
                advice.setFruit(fruit);
                advice.setMainfood(mainfood);
                advice.setEgg(egg);
                advice.setAnimal(animal);
                advice.setPotato(potato);
                advice.setFrequency(frequency);
                advice.setSide(side);
                advice.setGender(gender);

                advice.setHypoglycemia(hypoglycemia);
                advice.setMealtime(mealtime);
                advice.setExercise(exercise);
                advice.setInsulin(insulin);
                advice.setMeals(meals);
                advice.setLiquor(liquor);
                advice.setExtradrug(extradrug);
                advice.setElsedrug(elsedrug);
                advice.setBefore(before);

                List<String> strings = advice.AllAdvice();
                for(String string: strings) {
                    Log.d("Advice",string);
                }

                Intent intent = new Intent();
                intent.putExtra("advices",(Serializable)strings);
                intent.setClass(QAndAActivity.this, DisplayAdviceActivity.class);
                startActivity(intent);
            }



        }
    }

}