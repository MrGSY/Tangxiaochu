package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dblibrary.UserInfo;

public class Join3 extends AppCompatActivity {
    private Spinner spinner;
    private String birth;
    private Button NextStep2;
    private Button LastStep2;
    //private UserInfo user;
    private Intent intent_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join3);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Join3");

        intent_next=new Intent(Join3.this,Join4.class);

        //下一步按钮事件
        NextStep2=(Button)findViewById(R.id.NextStep2);
        NextStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner=(Spinner)findViewById(R.id.year);
                birth=spinner.getSelectedItem().toString();
                //将出生年份存进临时存储区
                UserJoinInfo.setUserBirth(birth);
                startActivity(intent_next);
            }
        });

        LastStep2=(Button)findViewById(R.id.LastStep2);
        LastStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
