package com.example.asus.tangtang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dblibrary.Dish;

import java.util.ArrayList;
import java.util.List;

public class FoodStreet extends AppCompatActivity {
    private List<FoodListItem> foodList=new ArrayList<>();
    private String search_content;
    private ImageButton goback;
    private SearchView searchView;
    private ListView listView;
    private FoodListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_street);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        adapter=new FoodListItemAdapter(FoodStreet.this, R.layout.foodlistitem, foodList);
        listView=(ListView)findViewById(R.id.FoodListView);
        searchView=(SearchView)findViewById(R.id.searchView) ;

        initFoodListItems();
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_content=query;
                if(!search_content.isEmpty()){
                    try {
                        Dish dish = Dish.findByName(search_content);
                        foodList.clear();
                        FoodListItem newitem=new FoodListItem(dish.getName(),dish.getComponent(),getResource(dish.getImage()));
                        foodList.add(newitem);
                        listView.setAdapter(adapter);
                    }
                    catch (Exception e) {
                        Toast.makeText(FoodStreet.this, "未找到", Toast.LENGTH_SHORT).show();
                        initFoodListItems();
                        listView.setAdapter(adapter);
                    }
                }
                else {
                    Toast.makeText(FoodStreet.this, "请输入菜名搜索", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            //过滤搜索内容
            @Override
            public boolean onQueryTextChange(String newText) {
                search_content=newText;
                if(search_content.isEmpty()){
                    //搜索内容为空则显示全部菜
                    initFoodListItems();
                    listView.setAdapter(adapter);
                }
                else {
                    //以关键字进行模糊搜索
                    foodList.clear();
                    List<Dish> dishes = Dish.findByName_multiple(search_content);
                    //将搜索结果添加至显示列表
                    for(Dish dish: dishes) {
                        FoodListItem newitem=new FoodListItem(dish.getName(),dish.getComponent(),getResource(dish.getImage()));
                        foodList.add(newitem);
                    }
                    listView.setAdapter(adapter);
                }
                return false;
            }
        });


        //注册item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodListItem foodListItem=foodList.get(position);
                String food_name=foodListItem.getName();
                Intent intent=new Intent(FoodStreet.this, ShowDish.class);
                intent.putExtra("extra_data", food_name);
                startActivity(intent);
            }
        });

        //返回
        goback=(ImageButton)findViewById(R.id.goback_foodstreet);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodStreet.this.finish();
                onBackPressed();
            }
        });
    }

    //由图片名称获取同名id
    private int getResource(String imageName) {
        Context ctx = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        return resId;
    }

    private void initFoodListItems(){
        //访问数据库添加食物项
        foodList.clear();
        List<Dish> dishes = Dish.findAllDishes();
        for(Dish dish: dishes){
            FoodListItem item=new FoodListItem(dish.getName(),dish.getComponent(),getResource(dish.getImage()));
            foodList.add(item);
        }
    }
}
