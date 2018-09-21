package com.example.asus.tangtang;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer timer;

    //跳转到登录界面
    private void TurnToNext(){
        Intent intent_begin=new Intent(MainActivity.this, Log_in.class);
        MainActivity.this.finish();
        startActivity(intent_begin);
    }

    @Override
    public void onBackPressed() {
        ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //状态栏透明化设置
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_main);

        //默认标题栏隐藏设置
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        //创建数据库
        createDatabase();

        //计时模块
        timer=new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                //此处可设置倒计时文本框
                //CountingDown.setText(String.format(getString(R.string.countdown),millisUntilFinished / 1000));
            }
            public void onFinish() {
                TurnToNext();
            }
        }.start();
    }

    private void createDatabase() {
        final int BUFFER_SIZE = 200000;
        final String DB_NAME = "alldata3.db"; //保存的数据库文件名
        final String PACKAGE_NAME = "com.example.asus.tangtang" ;
        final String DB_PATH = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME;  //在手机里存放数据库的位置
        final String dbPath = DB_PATH + "/databases/";
        final String dbfile = dbPath + DB_NAME;
        try {
            if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入
                ///
                File filepath = new File(dbPath);
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                ///
                if (filepath.exists()) {
                    InputStream is = getApplication().getResources().openRawResource(
                            R.raw.alldata3); //欲导入的数据库
                    FileOutputStream fos = new FileOutputStream(dbfile);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int count = 0;
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                } else {
                }
            } else {
                Log.d("test", "数据库已存在");
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
        }
    }

}
