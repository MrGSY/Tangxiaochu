package com.example.asus.tangtang;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dblibrary.UserInfo;

public class SetPersonInfo extends AppCompatActivity {
    private Button Set;
    private ImageButton goback;
    private TextView Gender;
    private TextView Birth;
    private EditText Height;
    private EditText Weight;
    private TextView Diagnosis;
    private TextView Type;
    private TextView textView;

    private String height;
    private String weight;
    private UserInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_person_info);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "SetPersonInfo");

        goback=(ImageButton)findViewById(R.id.goback_personinfo) ;
        Set=(Button)findViewById(R.id.set_pensoninfo_complete) ;

        Birth=(TextView)findViewById(R.id.birth_setPersonInfo);
        Diagnosis=(TextView)findViewById(R.id.diagnosis_setPersonInfo);
        Gender=(TextView)findViewById(R.id.gender_setPersonInfo) ;
        Type=(TextView)findViewById(R.id.type_setPersonInfo) ;
        Height=(EditText)findViewById(R.id.height_tochange);
        Weight=(EditText)findViewById(R.id.weight_tochange);
        textView=(TextView)findViewById(R.id.username_setpersoninfo);

        textView.setText(UserName.getUsername_saved());

        //访问数据库调取当前用户信息并显示
        try{
            user=UserInfo.getUser(UserName.getUsername_saved());

            switch (user.getGender()){
                case UserInfo.GENDER_MAIL: Gender.setText("男"); break;
                case UserInfo.GENDER_FEMAIL: Gender.setText("女"); break;
                default:break;
            }

            Birth.setText(String.valueOf(user.getBirth()));
            Height.setText(String.valueOf(user.getHight()));
            Weight.setText(String.valueOf(user.getWeight()));
            Diagnosis.setText(String.valueOf(user.getTime()));

            //用switch代替
            switch (user.getType()){
                case 1: Type.setText("1型糖尿病"); break;
                case 2: Type.setText("2型糖尿病"); break;
                case 3: Type.setText("妊娠糖尿病"); break;
                case 4: Type.setText("其他类型糖尿病"); break;
                default:break;
            }
        }
        catch (Exception e){
            ;
        }

        //提交修改按钮事件
        Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    user = UserInfo.getUser(UserName.getUsername_saved());

                    height=Height.getText().toString();
                    user.setHight(Double.parseDouble(height));

                    weight=Weight.getText().toString();
                    user.setWeight(Double.parseDouble(weight));
                }
                catch (Exception e){
                    ;
                }
                Toast.makeText(SetPersonInfo.this, "修改成功！",Toast.LENGTH_LONG).show();
            }
        });

        //顶栏返回按钮事件
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetPersonInfo.this.finish();
                onBackPressed();
            }
        });
    }
}
