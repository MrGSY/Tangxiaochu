package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.dblibrary.UserInfo;

import org.litepal.LitePal;

public class Join2 extends AppCompatActivity {
    //private UserInfo user;
    private Button LastStep1;
    private ImageButton Male;
    private ImageButton Female;
    private Intent intent_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Join2");

        intent_next=new Intent(Join2.this, Join3.class);

        LastStep1=(Button)findViewById(R.id.LastStep1);
        LastStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Male=(ImageButton)findViewById(R.id.male);
        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录男性性别到临时存储区
                UserJoinInfo.setUserGender(true);
                startActivity(intent_next);
            }
        });

        Female=(ImageButton)findViewById(R.id.female);
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录女性性别到临时存储区
                UserJoinInfo.setUserGender(false);
                startActivity(intent_next);
            }
        });
    }
}
