package com.example.asus.tangtang;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dblibrary.Dish;

import java.util.ArrayList;
import java.util.List;

public class Page_One extends AppCompatActivity {
    private DrawerLayout Page_One;
    private List<MenuListItem> menuItemList=new ArrayList<>();
    private ListView listView;
    private boolean backFlag=false;
    private TextView textView;

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
        setContentView(R.layout.activity_page__one);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }
        ToActivityDestroy.addDestoryActivity(this, "Page_One");

        replaceFragment(new Home_Page_fragment());

        //主页顶栏侧栏开启按钮
        Page_One=(DrawerLayout)findViewById(R.id.Page_One);
        findViewById(R.id.leftmenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page_One.openDrawer(Gravity.START);
            }
        });

        initMenuListItem();

        textView=(TextView)findViewById(R.id.username_menu);
        textView.setText(UserName.getUsername_saved());

        MenuListItemAdapter adapter=new MenuListItemAdapter(Page_One.this, R.layout.menulist_item, menuItemList);
        listView=(ListView)findViewById(R.id.menulist);
        listView.setAdapter(adapter);

        //侧栏项目点击时颜色改变
        listView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == true){
                    //获得焦点
                    listView.setSelector(R.color.colorCelanTop) ;
                }   else{
                    //失去焦点
                    listView.setSelector(R.color.colorCelanList) ;
                }
            }
        });

        //侧栏项目点击跳转事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuListItem menuListItem=menuItemList.get(position);
                switch (position){
                    case 0:
                        replaceFragment(new Home_Page_fragment());
                        break;
                    case 1:
                        Intent intent_foodstreet=new Intent(Page_One.this, FoodStreet.class);
                        startActivity(intent_foodstreet);
                        break;
                    case 2:
                        replaceFragment(new Encyclopedia_Fragment());
                        break;
                    case 3:
                        Intent intent_foodblacklist=new Intent(Page_One.this, FoodBlackList.class);
                        startActivity(intent_foodblacklist);
                        break;
                    case 4:
                        replaceFragment(new PersonInfo_fragment());
                        break;
                    case 5:
                        //同步数据
                        Toast.makeText(Page_One.this, "同步完成", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Page_One.super.onBackPressed();
                        break;
                    default:
                        break;
                }
                Page_One.closeDrawer(Gravity.START);
            }
        });
    }

    //初始化侧栏项目
    private void initMenuListItem(){
        MenuListItem Home=new MenuListItem("主页", R.drawable.home);
        menuItemList.add(Home);
        MenuListItem FoodStreet=new MenuListItem("美食街", R.drawable.foodstreet);
        menuItemList.add(FoodStreet);
        MenuListItem Encyclopedia=new MenuListItem("知识百科", R.drawable.encyclopedia);
        menuItemList.add(Encyclopedia);
        MenuListItem FoodBlackList=new MenuListItem("我的食物黑名单", R.drawable.foodlist);
        menuItemList.add(FoodBlackList);
        MenuListItem PersonInfo=new MenuListItem("个人信息",R.drawable.user);
        menuItemList.add(PersonInfo);
        MenuListItem SyncData=new MenuListItem("同步数据", R.drawable.delete);
        menuItemList.add(SyncData);
        MenuListItem Exit=new MenuListItem("退出",R.drawable.exit);
        menuItemList.add(Exit);
    }

    //动态加载碎片
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content_forreplacement, fragment);
        transaction.commit();
    }

}
