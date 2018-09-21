package com.example.asus.tangtang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class CommonSettings extends AppCompatActivity {
    private ImageButton goback;
    private Button switch_user;
    private Button sync_data;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_settings);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "CommonSettings");

        textView=(TextView)findViewById(R.id.username_commonsettings);
        textView.setText(UserName.getUsername_saved());

        //顶栏返回按钮
        goback=(ImageButton)findViewById(R.id.goback_commonsettings);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonSettings.this.finish();
                onBackPressed();
            }
        });

        //切换用户按钮
        switch_user=(Button)findViewById(R.id.switch_user);
        switch_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提示弹窗
                AlertDialog.Builder builder=new AlertDialog.Builder(CommonSettings.this);
                builder.setMessage("确定注销当前用户吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(CommonSettings.this, Log_in.class);
                        ToActivityDestroy.destoryActivity("Page_One");
                        ToActivityDestroy.destoryActivity("CommonSettings");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        //同步数据按钮
        sync_data=(Button)findViewById(R.id.sync_data);
        sync_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同步数据
                Toast.makeText(CommonSettings.this, "同步完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
