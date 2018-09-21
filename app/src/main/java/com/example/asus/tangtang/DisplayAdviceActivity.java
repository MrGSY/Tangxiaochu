package com.example.asus.tangtang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DisplayAdviceActivity extends AppCompatActivity {

    private LayoutInflater xInflater;
    private View advice_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advice);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "DisplayAdviceActivity");

        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        List<String> advices=(List<String>) getIntent().getSerializableExtra("advices");

        LinearLayout test_layout=(LinearLayout)findViewById(R.id.lly_test);

        for(String string:advices){
            advice_view=xInflater.inflate(R.layout.advice_layout, null);
            TextView txt_advice=(TextView)advice_view.findViewById(R.id.txt_advice_item);
            txt_advice.setText(string);
            test_layout.addView(advice_view);
        }

        Button button=(Button)findViewById(R.id.button_get_it);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayAdviceActivity.this, Page_One.class);
                ToActivityDestroy.destoryActivity("QAndAActivity");
                ToActivityDestroy.destoryActivity("DisplayAdviceActivity");
                startActivity(intent);
            }
        });
    }
}
