package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblibrary.MD5Util;
import com.example.dblibrary.UserInfo;

import org.litepal.LitePal;


public class Join extends AppCompatActivity {
    private EditText NewName;
    private EditText NewPassword;
    private Button NextStep1;
    private UserInfo user;

    private String name;
    private String password;
    private EditText Confirm;
    private String confirm;

    private Intent intent_join_step1;

    @Override
    public void onBackPressed() {
        Toast.makeText(Join.this, "您取消了这次注册", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Join");

        NewName=(EditText)findViewById(R.id.NewName);
        NewPassword=(EditText)findViewById(R.id.NewPassword);
        Confirm=(EditText)findViewById(R.id.Confirm);

        intent_join_step1=new Intent(Join.this,Join2.class);

        //下一步按钮事件
        NextStep1=(Button)findViewById(R.id.NextStep1);
        NextStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=NewName.getText().toString();//name用于临时存储用户注册昵称
                password=NewPassword.getText().toString();//password用于临沭存储用户注册密码
                confirm=Confirm.getText().toString();

                if(!name.isEmpty()){
                    //查询用户名是否存在
                    try{
                        if(!password.isEmpty()){
                            if(confirm.equals(password)){
                                if(password.length()>=6){
                                    if(UserInfo.isUserEmpty(name)){
                                        UserJoinInfo.setUserPassword(password);
                                        UserName.setUsername_saved(name);
                                        startActivity(intent_join_step1);
                                    }
                                    else {
                                        Toast.makeText(Join.this,"该用户名已存在！",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(Join.this,"密码应不小于6位！",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Join.this,"两次输入密码应相同！",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Join.this,"请输入密码！",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        ;
                    }
                }
                else{
                    Toast.makeText(Join.this,"请输入用户名！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}