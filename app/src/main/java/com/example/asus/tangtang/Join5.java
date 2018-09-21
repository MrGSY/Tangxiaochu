package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dblibrary.MD5Util;
import com.example.dblibrary.UserInfo;

public class Join5 extends AppCompatActivity {
    private Button LastStep4;
    private Button FinalConfirm;
    private Intent intent;
    private Spinner Type;
    private Spinner Diagnosis_year;
    private String type;
    private String diagnosis_year;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join5);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Join5");

        Type=(Spinner)findViewById(R.id.type);
        Diagnosis_year=(Spinner)findViewById(R.id.diagnosis_year);

        intent=new Intent(Join5.this, Log_in.class);

        LastStep4=(Button)findViewById(R.id.LastStep4);
        LastStep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //确认按钮事件
        FinalConfirm=(Button)findViewById(R.id.FinalConfirm);
        FinalConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=Type.getSelectedItem().toString();
                diagnosis_year=Diagnosis_year.getSelectedItem().toString();
                //创建账户并将全部用户信息存入数据库
                try{
                    //创建账户
                    user = UserInfo.register(UserName.getUsername_saved(), MD5Util.encrypBy(UserJoinInfo.getUserPassword()));
                    //存入性别
                    if(UserJoinInfo.isUserGender()==true){
                        user.setGender(UserInfo.GENDER_MAIL);
                    }
                    else {
                        user.setGender(UserInfo.GENDER_FEMAIL);
                    }
                    //存入出生年份
                    user.setBirth(Integer.parseInt(UserJoinInfo.getUserBirth()));
                    //存入身高体重
                    user.setWeight(Double.parseDouble(UserJoinInfo.getUserWeight()));
                    user.setHight(Double.parseDouble(UserJoinInfo.getUserHeight()));
                    //存入患病类型和确诊时间
                    user.setTime(Integer.parseInt(diagnosis_year));
                    switch(type){
                        case "1型糖尿病": user.setType(1); break;
                        case "2型糖尿病": user.setType(2); break;
                        case "妊娠糖尿病": user.setType(3); break;
                        case "其他类型糖尿病": user.setType(4); break;
                        default:break;
                    }

                }
                catch (Exception e){
                    Toast.makeText(Join5.this, "账户创建失败！请重试！",Toast.LENGTH_SHORT).show();
                }
                finally {
                    Toast.makeText(Join5.this,"完成注册!请登录", Toast.LENGTH_SHORT).show();
                    ToActivityDestroy.destoryActivity("Log_in");
                    ToActivityDestroy.destoryActivity("Join");
                    ToActivityDestroy.destoryActivity("Join2");
                    ToActivityDestroy.destoryActivity("Join3");
                    ToActivityDestroy.destoryActivity("Join4");
                    ToActivityDestroy.destoryActivity("Join5");
                    startActivity(intent);
                }
            }
        });
    }
}
