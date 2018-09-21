package com.example.asus.tangtang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblibrary.UserInfo;

public class Log_in extends AppCompatActivity {
    private EditText username;
    private EditText userpassword;
    private String username_forcheck;
    private String userpassword_forcheck;
    private UserInfo user;
    private Button button_login;
    private Button button_join;
    private Intent intent_login;
    private Intent intent_join;
    private boolean backFlag=false;

    @Override
    public void onBackPressed() {
        if (backFlag) {
            //退出
            super.onBackPressed();
        } else {
            //单击一次提示信息
            Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT).show();
            backFlag = true;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //1秒之后，修改flag的状态
                    backFlag = false;
                }                ;
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Log_in");

        username=(EditText)findViewById(R.id.UserName) ;
        userpassword=(EditText)findViewById(R.id.UserPassword) ;

        intent_login=new Intent(Log_in.this,Page_One.class);
        intent_join=new Intent(Log_in.this,Join.class);

        //显示刚注册的用户名
        username.setText(UserName.getUsername_saved());

        button_login=(Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_forcheck=username.getText().toString();
                userpassword_forcheck=userpassword.getText().toString();
                //验证登陆信息-调用方法访问用户信息数据库查验用户信息
                try {
                    user = UserInfo.getUser(username_forcheck,userpassword_forcheck);
                    UserName.setUsername_saved(username_forcheck);
                    Toast.makeText(Log_in.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    Log_in.this.finish();
                    startActivity(intent_login);
                }
                catch (Exception e) {
                    //信息验证失败
                    Toast.makeText(Log_in.this,"用户信息有误！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_join=(Button)findViewById(R.id.button_join);
        button_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_join);
            }
        });
    }
}
