package com.example.asus.tangtang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dblibrary.BGAdvice;
import com.example.dblibrary.DailyBG;
import com.example.dblibrary.Dish;


import java.util.ArrayList;
import java.util.List;

public class Home_Page_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page_fragment, container, false);
    }

    private double i;
    private TextView xuetang_advice;
    private EditText xuetang_upload;
    private Button Upload;
    private Spinner select_type;
    private String type;
    private String advice;

    private Fragment XuetangChart;
    private FragmentTransaction transaction;

    private ImageView breakfast1_image;
    private ImageView breakfast2_image;
    private ImageView lunch1_image;
    private ImageView lunch2_image;
    private ImageView lunch3_image;
    private ImageView dinner1_image;
    private ImageView dinner2_image;
    private ImageView dinner3_image;
    private TextView breakfast1_name;
    private TextView breakfast2_name;
    private TextView lunch1_name;
    private TextView lunch2_name;
    private TextView lunch3_name;
    private TextView dinner1_name;
    private TextView dinner2_name;
    private TextView dinner3_name;
    private TextView  breakfast1_Hot;
    private TextView breakfast2_Hot;
    private TextView lunch1_Hot;
    private TextView lunch2_Hot;
    private TextView lunch3_Hot;
    private TextView dinner1_Hot;
    private TextView dinner2_Hot;
    private TextView dinner3_Hot;

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //载入图表子碎片
        XuetangChart = new Xuetang_Chart_Fragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.xuetang_forreplace, XuetangChart).commit();

        //今日血糖模块
        TodayXuetang();

        //今日推荐模块
        TodayRecommend();
    }

    //今日推荐模块
    private void TodayRecommend(){
        breakfast1_image=(ImageView)getActivity().findViewById(R.id.breakfast1_image);
        breakfast2_image=(ImageView)getActivity().findViewById(R.id.breakfast2_image);
        lunch1_image=(ImageView)getActivity().findViewById(R.id.lunch1_image);
        lunch2_image=(ImageView)getActivity().findViewById(R.id.lunch2_image);
        lunch3_image=(ImageView)getActivity().findViewById(R.id.lunch3_image);
        dinner1_image=(ImageView)getActivity().findViewById(R.id.dinner1_image);
        dinner2_image=(ImageView)getActivity().findViewById(R.id.dinner2_image);
        dinner3_image=(ImageView)getActivity().findViewById(R.id.dinner3_image);
        breakfast1_name=(TextView)getActivity().findViewById(R.id.breakfast1_name);
        breakfast2_name=(TextView)getActivity().findViewById(R.id.breakfast2_name);
        lunch1_name=(TextView)getActivity().findViewById(R.id.lunch1_name);
        lunch2_name=(TextView)getActivity().findViewById(R.id.lunch2_name);
        lunch3_name=(TextView)getActivity().findViewById(R.id.lunch3_name);
        dinner1_name=(TextView)getActivity().findViewById(R.id.dinner1_name);
        dinner2_name=(TextView)getActivity().findViewById(R.id.dinner2_name);
        dinner3_name=(TextView)getActivity().findViewById(R.id.dinner3_name);
        breakfast1_Hot=(TextView)getActivity().findViewById(R.id.breakfast1_Hot);///
        breakfast2_Hot=(TextView)getActivity().findViewById(R.id.breakfast2_Hot);///
        lunch1_Hot=(TextView)getActivity().findViewById(R.id.lunch1_Hot);///
        lunch2_Hot=(TextView)getActivity().findViewById(R.id.lunch2_Hot);///
        lunch3_Hot=(TextView)getActivity().findViewById(R.id.lunch3_Hot);///
        dinner1_Hot=(TextView)getActivity().findViewById(R.id.dinner1_Hot);///
        dinner2_Hot=(TextView)getActivity().findViewById(R.id.dinner2_Hot);///
        dinner3_Hot=(TextView)getActivity().findViewById(R.id.dinner3_Hot);///

        List<Dish> dish_breakfast = new ArrayList<Dish>();
        List<Dish> dish_lunch = new ArrayList<Dish>();
        List<Dish> dish_dinner = new ArrayList<Dish>();

        //推荐菜品图像、名称和热量值加载内容
        try {
            dish_breakfast = Dish.getAdvicedMeal(Dish.TYPE_BREAKFAST);
            breakfast1_image.setImageResource(getResource(dish_breakfast.get(0).getImage()));
            breakfast1_name.setText(dish_breakfast.get(0).getName());
            breakfast1_Hot.setText(String.format("%d",dish_breakfast.get(0).getEnergy()));///
            setHotColor(breakfast1_Hot,dish_breakfast.get(0).getEnergy());///

            breakfast2_image.setImageResource(getResource(dish_breakfast.get(1).getImage()));
            breakfast2_name.setText(dish_breakfast.get(1).getName());
            breakfast2_Hot.setText(String.format("%d",dish_breakfast.get(1).getEnergy()));///
            setHotColor(breakfast2_Hot,dish_breakfast.get(1).getEnergy());///
        }
        catch (Exception e){
            ;
        }

        try {
            dish_lunch = Dish.getAdvicedMeal(Dish.TYPE_LUNCH);
            lunch1_image.setImageResource(getResource(dish_lunch.get(0).getImage()));
            lunch1_name.setText(dish_lunch.get(0).getName());
            lunch1_Hot.setText(String.format("%d",dish_lunch.get(0).getEnergy()));///
            setHotColor(lunch1_Hot,dish_lunch.get(0).getEnergy());///

            lunch2_image.setImageResource(getResource(dish_lunch.get(1).getImage()));
            lunch2_name.setText(dish_lunch.get(1).getName());
            lunch2_Hot.setText(String.format("%d",dish_lunch.get(1).getEnergy()));///
            setHotColor(lunch2_Hot,dish_lunch.get(1).getEnergy());///

            lunch3_image.setImageResource(getResource(dish_lunch.get(2).getImage()));
            lunch3_name.setText(dish_lunch.get(2).getName());
            lunch3_Hot.setText(String.format("%d",dish_lunch.get(2).getEnergy()));///
            setHotColor(lunch3_Hot,dish_lunch.get(2).getEnergy());///
        }
        catch (Exception e){
            ;
        }

        try {
            dish_dinner = Dish.getAdvicedMeal(Dish.TYPE_DINNER);
            dinner1_image.setImageResource(getResource(dish_dinner.get(0).getImage()));
            dinner1_name.setText(dish_dinner.get(0).getName());
            dinner1_Hot.setText(String.format("%d",dish_dinner.get(0).getEnergy()));///
            setHotColor(dinner1_Hot,dish_dinner.get(0).getEnergy());///

            dinner2_image.setImageResource(getResource(dish_dinner.get(1).getImage()));
            dinner2_name.setText(dish_dinner.get(1).getName());
            dinner2_Hot.setText(String.format("%d",dish_dinner.get(1).getEnergy()));///
            setHotColor(dinner2_Hot,dish_dinner.get(1).getEnergy());///

            dinner3_image.setImageResource(getResource(dish_dinner.get(2).getImage()));
            dinner3_name.setText(dish_dinner.get(2).getName());
            dinner3_Hot.setText(String.format("%d",dish_dinner.get(2).getEnergy()));///
            setHotColor(dinner3_Hot,dish_dinner.get(2).getEnergy());///
        }
        catch (Exception e){
            ;
        }

        //图片点击事件，将食物名传至食物详情活动后跳转
        final Intent intent=new Intent(getActivity(), ShowDish.class);
        breakfast1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", breakfast1_name.getText().toString());
                startActivity(intent);
            }
        });

        breakfast2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", breakfast2_name.getText().toString());
                startActivity(intent);
            }
        });

        lunch1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", lunch1_name.getText().toString());
                startActivity(intent);
            }
        });

        lunch2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", lunch2_name.getText().toString());
                startActivity(intent);
            }
        });

        lunch3_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", lunch3_name.getText().toString());
                startActivity(intent);
            }
        });

        dinner1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", dinner1_name.getText().toString());
                startActivity(intent);
            }
        });

        dinner2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", dinner2_name.getText().toString());
                startActivity(intent);
            }
        });

        dinner3_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra_data", dinner3_name.getText().toString());
                startActivity(intent);
            }
        });
    }

    //今日血糖模块
    private void TodayXuetang(){
        xuetang_upload=(EditText)getActivity().findViewById(R.id.xuetang_upload);
        Upload=(Button)getActivity().findViewById(R.id.button_xuetang_upload);
        xuetang_advice=(TextView)getActivity().findViewById(R.id.xuetang_advice_text) ;
        select_type=(Spinner)getActivity().findViewById(R.id.select_type);

        //提交按钮事件
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=select_type.getSelectedItem().toString();

                if(xuetang_upload.getText().toString().isEmpty()){
                    //文本框为空提示
                    Toast.makeText(getActivity(), "请输入血糖数据", Toast.LENGTH_SHORT).show();
                }
                else {
                    i=Double.parseDouble(xuetang_upload.getText().toString());
                    advice=DailyBG.updateBGInfo(i, DailyBG.toType(type));

                    Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                    xuetang_advice.setText(advice);

                    //血糖数值异常
                    if(BGAdvice.query(i, DailyBG.toType(type))){
                        //人性化优化：弹窗询问是否跳转到问卷界面
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("您的血糖数值异常，是否需要进行自查？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent_qanda=new Intent(getActivity(), QAndAActivity.class);
                                intent_qanda.putExtra("TYPE", type);
                                intent_qanda.putExtra("level", i);
                                startActivity(intent_qanda);
                            }
                        });
                        builder.setNegativeButton("取消", null);
                        builder.show();
                    }
                }
                //实时刷新血糖图表，未实现
            }
        });
    }

    //getActivity()在fragment中获取Activity实例，在Activity中声明不需要getActivity(),
    //getResource(String imageName)用于输入图片名，获取同名图片id，要求图片名与图片id相同
    private int getResource(String imageName) {
        Context ctx = getActivity().getBaseContext();
        int resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        return resId;
    }

    //热量值颜色会随值改变
    private void setHotColor(TextView Hot,int Hotnumber){///
        if(Hotnumber>=110) Hot.setTextColor(Color.rgb(158,0,57));///
        else Hot.setTextColor(Color.rgb(0,88,38));///
    }///

}
