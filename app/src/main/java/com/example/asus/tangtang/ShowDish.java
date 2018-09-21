package com.example.asus.tangtang;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dblibrary.Dish;
import com.github.mikephil.charting.charts.PieChart;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShowDish extends AppCompatActivity {

    private PieChart mChart3;
    @Nullable
    private TextView Name;
    private TextView Protein;
    private TextView Fat;
    private TextView Sugar;
    private TextView Hot;
    private TextView URL;
    private TextView Component;
    private TextView Advice;
    private ImageView Image;
    private ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dish);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        Intent intent=getIntent();
        String get_food_name=intent.getStringExtra("extra_data");

        LitePal.getDatabase();

        mChart3 = (PieChart) findViewById(R.id.chart3);
        Name = (TextView) findViewById(R.id.Name);
        Protein = (TextView) findViewById(R.id.Protein);
        Fat = (TextView) findViewById(R.id.Fat);
        Sugar = (TextView) findViewById(R.id.Suger);
        Hot = (TextView) findViewById(R.id.Hot);
        URL = (TextView) findViewById(R.id.URL);
        Component = (TextView) findViewById(R.id.Compoent);
        Advice = (TextView) findViewById(R.id.Advice);
        Image = (ImageView) findViewById(R.id.FoodImage);
        goback=(ImageButton) findViewById(R.id.goback_showdish);
        String name = get_food_name;

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try{
            final Dish dish = Dish.findByName(name);
            Name.setText(name);
            Protein.setText(String.format("%d",dish.getProtein()));
            Log.d("finddish",Integer.toString(dish.getEnergy()));
            Fat.setText(String.format("%d",dish.getFat()));
            Sugar.setText(String.format("%d",dish.getCarbohydrate()));
            Hot.setText(String.format("%d",dish.getEnergy()));

            URL.setText(dish.getURL());

            URL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(dish.getURL()));
                    startActivity(intent);
                }
            });
            Component.setText("\n"+dish.getComponent()+"\n");
            Advice.setText(dish.getAdvice());
            int resID = LitePalApplication.getContext().getResources().getIdentifier(
                    dish.getImage(), "drawable", LitePalApplication.getContext().getPackageName());
            Drawable drawable = LitePalApplication.getContext().getResources().getDrawable(resID);
            Image.setImageDrawable(drawable);
            PieChartUtils.initView(mChart3, this,
                    dish.getCarbohydrate(), dish.getProtein(),dish.getFat());
        }
        catch (Exception e) {
            Log.d("test", "gg");
        }
    }
    private void createDatabase() {
        final int BUFFER_SIZE = 200000;
        final String DB_NAME = "alldata3.db"; //保存的数据库文件名
        final String PACKAGE_NAME = "com.example.mrgsy.dishactivity" ;
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
            ;
        }
    }

}
