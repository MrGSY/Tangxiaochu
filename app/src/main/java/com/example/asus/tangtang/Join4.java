package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dblibrary.UserInfo;

public class Join4 extends AppCompatActivity {
    private EditText Height;
    private EditText Weight;
    private String height;
    private String weight;
    private UserInfo user;
    private Button NextStep3;
    private Button LastStep3;
    private Intent intent_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join4);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Join4");

        Height=(EditText)findViewById(R.id.height);
        Weight=(EditText)findViewById(R.id.weight);

        intent_next=new Intent(Join4.this,Join5.class);

        //下一步按钮事件
        NextStep3=(Button)findViewById(R.id.NextStep3);
        NextStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height=Height.getText().toString();
                weight=Weight.getText().toString();
                //将身高体重存入临时存储区
                UserJoinInfo.setUserHeight(height);
                UserJoinInfo.setUserWeight(weight);
                startActivity(intent_next);
            }
        });

        LastStep3=(Button)findViewById(R.id.LastStep3);
        LastStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
