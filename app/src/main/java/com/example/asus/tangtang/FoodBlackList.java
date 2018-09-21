package com.example.asus.tangtang;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dblibrary.Dish;
import com.hitomi.refresh.view.FunGameRefreshView;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class FoodBlackList extends AppCompatActivity {
    private List<BlackListItem> blackListItems=new ArrayList<>();
    private ImageButton goback;
    private Button button_add;
    private EditText Foodtoadd;
    private BlackListItemAdapter adapter;
    private ListView listView;
    private FunGameRefreshView funGameRefreshView;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_black_list);
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        Foodtoadd=(EditText)findViewById(R.id.foodtoaddtoblacklist);
        funGameRefreshView=(FunGameRefreshView)findViewById(R.id.game);

        initBlackList();

        adapter=new BlackListItemAdapter(FoodBlackList.this, R.layout.foodblacklistitem, blackListItems);
        listView=(ListView)findViewById(R.id.foodblacklist_listview);
        listView.setAdapter(adapter);

        //小游戏组件设置
        funGameRefreshView.setOnRefreshListener(new FunGameRefreshView.FunGameRefreshListener() {
            @Override
            public void onPullRefreshing() {
                //下拉后停留5s后自动收起
                SystemClock.sleep(5000);
            }

            @Override
            public void onRefreshComplete() {
                listView.setAdapter(adapter);
            }
        });

        //黑名单食物项点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                //提示弹窗
                AlertDialog.Builder builder=new AlertDialog.Builder(FoodBlackList.this);
                builder.setMessage("点击食物可以将该食物移出黑名单，您确定移除吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //将该item从listview中移除
                        blackListItems.remove(blackListItems.get(position));
                        //从数据库中清除，检索并清除
                        LinearLayout linearLayout=(LinearLayout) listView.getChildAt(position);
                        TextView textView=(TextView)linearLayout.findViewById(R.id.foodblacklistitem_name) ;
                        String content=textView.getText().toString();
                        Dish.setBlacklistByName(content, 0);
                        Toast.makeText(FoodBlackList.this, content+",已从您的黑名单中移除", Toast.LENGTH_SHORT).show();
                        listView.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        //顶栏返回按钮
        goback=(ImageButton)findViewById(R.id.goback_foodblacklist);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //添加按钮事件
        button_add=(Button)findViewById(R.id.addtoblacklist);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Foodtoadd.getText().toString();
                //调用数据库，检查菜名是否存在
                try {
                    Dish.findByName(name);
                    Dish.setBlacklistByName(name, 1);
                    //若存在
                    initBlackList();
                    Toast.makeText(FoodBlackList.this, name+",已添加至黑名单", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(adapter);
                }
                catch (Exception e) {
                    //不存在
                    Toast.makeText(FoodBlackList.this, name+",不存在！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initBlackList(){
        //访问数据库读取食物黑名单食物名称，添加至显示列表
        blackListItems.clear();
        List<Dish> dishes=Dish.getBlacklist();
        for(Dish dish:dishes){
            BlackListItem item=new BlackListItem(dish.getName());
            blackListItems.add(item);
        }
    }
}
