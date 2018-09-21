package com.example.asus.tangtang;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dblibrary.DailyBG;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XuetangChart extends AppCompatActivity {

///改起
    private TextView textView_max_kongfu;
    private TextView textView_max_fanhou;
    private TextView textView_max_shuiqian;
    private TextView textView_min_kongfu;
    private TextView textView_min_fanhou;
    private TextView textView_min_shuiqian;
    private TextView textView_daytext;
    private TextView textView_mean;
    private TextView textView_percent;
    private float max;
    private float min;
    private float percent;
    private float mean;
    private float max_kongfu;
    private float max_fanhou;
    private float max_shuqian;
    private float min_kongfu;
    private float min_fanhou;
    private float min_shuqian;
///改终


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xeutang_chart);

        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null){
            actionbar.hide();
        }

        ImageButton goback=(ImageButton)findViewById(R.id.goback_xuetangchart);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final LineChart lineChart = initView();
        final XAxis xAxis = modify();
        lineChart.getLegend().setEnabled(false);

        /*
        需要传入数据有
        每日：当天的血糖值数组（七个数值，从前到后依次读入）BG[0]读入x = 7,
        每周：三种血糖值对应的数组(所有的血糖值入Entry），数组中的血糖值与日期一一对应（v=7对应'当前月[0]'+'当前日[0]'，减七次得出结果）
        每月：三种血糖值对应的数组（所有血糖值入Entry），数组中的血糖值与日期一一对应
        注：血糖值从获得数组进入Entry，横坐标值每隔五个获取一次进入setvalueformatter
         */

        Description description = new Description();
        description.setText("每日血糖（单位mmol/L）");
        description.setTextSize(15);
        description.setPosition(975f, 60f);
        description.setTextColor(Color.GRAY);
        lineChart.setDescription(description);

        xAxis.setAxisMinimum(0f);//设置x轴的最小值
        xAxis.setAxisMaximum(8f);//设置最大值
        xAxis.setLabelCount(7);//绘制七个标签
        List<Entry> values1 = new ArrayList<>();

        Calendar today = Calendar.getInstance();
        final int year, month, date;
        year = today.get(Calendar.YEAR);
        month = today.get((Calendar.MONTH)) + 1;
        date = today.get(Calendar.DATE);
        DailyBG todayBG = DailyBG.getDailyBG(year, month, date);

///改起
        max = (float) todayBG.getBG(1);
        min = (float) todayBG.getBG(1);
        mean = (float) todayBG.getBG(1);
        percent = 0;
        int count = 0;
///改终

        for (int i = 1; i < 8; i++) {
            if ((float) todayBG.getBG(i) != 0) {
                values1.add((new Entry(i, (float) todayBG.getBG(i))));
///改起
                count++;
                if (max <= (float) todayBG.getBG(i))
                    max = (float) todayBG.getBG(i);
                if (min >= (float) todayBG.getBG(i))
                    min = (float) todayBG.getBG(i);
                mean = mean + (float) todayBG.getBG(i);
                if( todayBG.getBG(i)>=4.4 &&  todayBG.getBG(i)<= 10.0)
                    percent++;
            }
        }


        mean = mean/count;
        mean = (float)(Math.round(mean*100))/100;
        percent=percent/count;
        percent = (float)(Math.round(percent*100))/100;
        max = (float)(Math.round(max*100))/100;
        min = (float)(Math.round(min*100))/100;

        textView_daytext = (TextView)findViewById(R.id.suger_daytext);
        textView_daytext.setText("每日血糖分析");
        settext();
        textView_max_fanhou.setTextSize(18);
        textView_min_fanhou.setTextSize(18);

        textView_max_fanhou.setText(""+max);
        textView_max_kongfu.setText("");
        textView_max_shuiqian.setText("");
        textView_min_fanhou.setText(""+min);
        textView_min_kongfu.setText("");
        textView_min_shuiqian.setText("");
        textView_mean.setText(""+mean);
        textView_percent.setText(""+percent);

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if (v == 1) {
                    return "空腹";
                } else if (v == 2) {
                    return "早餐后";
                } else if (v == 3) {
                    return "午餐前";
                } else if (v == 4) {
                    return "午餐后";
                } else if (v == 5) {
                    return "晚餐前";
                } else if (v == 6) {
                    return "晚餐后";
                } else if (v == 7) {
                    return "睡前";
                } else return "";
            }
        });
        setChartData(lineChart, values1, values1, values1);
///此处将button的findview提前
        final Button button1 = (Button) findViewById(R.id.button_day);
        final Button button2 = (Button) findViewById(R.id.button_week);
        final Button button3 = (Button) findViewById(R.id.button_month);

///改起
        button1.setTextColor(Color.argb(255,255,255,255));
        button2.setTextColor(Color.argb(255,102,96,93));
        button3.setTextColor(Color.argb(255,102,96,93));

        button1.setBackgroundColor(Color.argb(255,102,96,93));
        button2.setBackgroundColor(Color.argb(255,255,255,255));
        button3.setBackgroundColor(Color.argb(255,255,255,255));
///改终

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

///改起
                button1.setTextColor(Color.argb(255,255,255,255));
                button2.setTextColor(Color.argb(255,102,96,93));
                button3.setTextColor(Color.argb(255,102,96,93));

                button1.setBackgroundColor(Color.argb(255,102,96,93));
                button2.setBackgroundColor(Color.argb(255,255,255,255));
                button3.setBackgroundColor(Color.argb(255,255,255,255));
///改终

                initView();
                modify();
                lineChart.getLegend().setEnabled(false);
                Description description = new Description();
                description.setText("每日血糖（单位mmol/L）");
                description.setTextSize(15);
                description.setPosition(975f, 60f);
                description.setTextColor(Color.GRAY);
                lineChart.setDescription(description);

                xAxis.setAxisMinimum(0f);//设置x轴的最小值
                xAxis.setAxisMaximum(8f);//设置最大值
                xAxis.setLabelCount(7);//绘制七个标签
                lineChart.getLegend().setEnabled(false);
                List<Entry> values1 = new ArrayList<>();

                Calendar today = Calendar.getInstance();
                int year, month, date;
                year = today.get(Calendar.YEAR);
                month = today.get((Calendar.MONTH)) + 1;
                date = today.get(Calendar.DATE);
                DailyBG todayBG = DailyBG.getDailyBG(year, month, date);

 ///改起
                max = (float) todayBG.getBG(1);
                min = (float) todayBG.getBG(1);
                mean = (float) todayBG.getBG(1);
                percent = 0;
                int count = 0;
///改终

                for (int i = 1; i < 8; i++) {
                    if ((float) todayBG.getBG(i) != 0) {
                        values1.add((new Entry(i, (float) todayBG.getBG(i))));
///改起
                        count++;
                        if (max <= (float) todayBG.getBG(i))
                            max = (float) todayBG.getBG(i);
                        if (min >= (float) todayBG.getBG(i))
                            min = (float) todayBG.getBG(i);
                        mean = mean + (float) todayBG.getBG(i);
                        if( todayBG.getBG(i)>=4.4 &&  todayBG.getBG(i)<= 10.0)
                            percent++;
                    }
                }

                mean = mean/count;
                mean = (float)(Math.round(mean*100))/100;
                percent=percent/count;
                percent = (float)(Math.round(percent*100))/100;
                max = (float)(Math.round(max*100))/100;
                min = (float)(Math.round(min*100))/100;

                textView_daytext = (TextView)findViewById(R.id.suger_daytext);
                textView_daytext.setText("今日血糖分析");
                settext();

                textView_max_fanhou.setTextSize(18);
                textView_min_fanhou.setTextSize(18);


                textView_max_fanhou.setText(""+max);
                textView_max_kongfu.setText("");
                textView_max_shuiqian.setText("");
                textView_min_fanhou.setText(""+min);
                textView_min_kongfu.setText("");
                textView_min_shuiqian.setText("");
                textView_mean.setText(""+mean);
                textView_percent.setText(""+percent);

                lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float v, AxisBase axisBase) {
                        if (v == 1) {
                            return "空腹";
                        } else if (v == 2) {
                            return "早餐后";
                        } else if (v == 3) {
                            return "午餐前";
                        } else if (v == 4) {
                            return "午餐后";
                        } else if (v == 5) {
                            return "晚餐前";
                        } else if (v == 6) {
                            return "晚餐后";
                        } else if (v == 7) {
                            return "睡前";
                        } else if (v == 0) {
                            return "";
                        } else return "";
                    }
                });
                setChartData(lineChart, values1, values1, values1);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {
///起
                button2.setTextColor(Color.argb(255,255,255,255));
                button1.setTextColor(Color.argb(255,102,96,93));
                button3.setTextColor(Color.argb(255,102,96,93));

                button2.setBackgroundColor(Color.argb(255,102,96,93));
                button1.setBackgroundColor(Color.argb(255,255,255,255));
                button3.setBackgroundColor(Color.argb(255,255,255,255));
///终

                initView();
                modify();
                lineChart.getLegend().setEnabled(true);
                Description description = new Description();
                description.setText("每周血糖（单位mmol/L）");
                description.setTextSize(15);
                description.setPosition(975f, 60f);
                description.setTextColor(Color.GRAY);
                lineChart.setDescription(description);

                xAxis.setAxisMinimum(0f);//设置x轴的最小值
                xAxis.setAxisMaximum(8f);//设置最大值
                xAxis.setLabelCount(7);//绘制七个标签

                List<Entry> values2 = new ArrayList<>();
                List<Entry> values3 = new ArrayList<>();
                List<Entry> values4 = new ArrayList<>();
                final List<DailyBG> weeklist = DailyBG.weekList();

///起
                int count = 0;
                mean = 0;
                percent = 0;
                max_kongfu = 0;
                max_fanhou = 0;
                max_shuqian = 0;
                min_kongfu = (float)weeklist.get(0).getBG(1);
                min_fanhou = (float)weeklist.get(0).getBG(2);
                min_shuqian = (float)weeklist.get(0).getBG(3);

                for (int i = 0; i < weeklist.size(); i++) {
                    DailyBG dailyBG = weeklist.get(i);
                    for (int j = 1; j < 4; j++) {

                        if((float) dailyBG.getBG(j)!=0)
                            switch (j) {
                                case 1:
                                    values2.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_kongfu<(float) dailyBG.getBG(j))
                                        max_kongfu = (float) dailyBG.getBG(j);
                                    if(min_kongfu>(float) dailyBG.getBG(j))
                                        min_kongfu = (float) dailyBG.getBG(j);
                                    mean = mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                                case 2:
                                    values3.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_fanhou<(float) dailyBG.getBG(j))
                                        max_fanhou = (float) dailyBG.getBG(j);
                                    if(min_fanhou>(float) dailyBG.getBG(j))
                                        min_fanhou = (float) dailyBG.getBG(j);
                                    mean = mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                                case 3:
                                    values4.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_shuqian<(float) dailyBG.getBG(j))
                                        max_shuqian = (float) dailyBG.getBG(j);
                                    if(min_shuqian>(float) dailyBG.getBG(j))
                                        min_shuqian = (float) dailyBG.getBG(j);
                                    mean= mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                            }
                    }
                }

                mean = mean/count;
                mean = (float)(Math.round(mean*100))/100;
                percent = percent/count;
                percent = (float)(Math.round(percent*100))/100;
                max_kongfu = (float)(Math.round(max_kongfu*100))/100;
                min_kongfu = (float)(Math.round(min_kongfu*100))/100;

                max_fanhou = (float)(Math.round(max_fanhou*100))/100;
                min_fanhou = (float)(Math.round(min_fanhou*100))/100;

                max_shuqian = (float)(Math.round(max_shuqian*100))/100;
                min_shuqian = (float)(Math.round(min_shuqian*100))/100;


                textView_daytext = (TextView)findViewById(R.id.suger_daytext);
                textView_daytext.setText("本周血糖分析");
                settext();
                textView_max_fanhou.setTextSize(15);
                textView_min_fanhou.setTextSize(15);
                textView_max_kongfu.setTextSize(15);
                textView_min_kongfu.setTextSize(15);
                textView_max_shuiqian.setTextSize(15);
                textView_min_shuiqian.setTextSize(15);

                textView_max_fanhou.setText("饭后："+max_fanhou);
                textView_max_kongfu.setText("空腹："+max_kongfu);
                textView_max_shuiqian.setText("睡前："+max_shuqian);
                textView_min_fanhou.setText("饭后："+min_fanhou);
                textView_min_kongfu.setText("空腹："+min_kongfu);
                textView_min_shuiqian.setText("睡前："+min_shuqian);
                textView_mean.setText(""+mean);
                textView_percent.setText(""+percent);

///终

                lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float v, AxisBase axisBase) {
                        int i = (int) v;
                        i--;
                        String ret;
                        if (i < 0) ret = " ";
                        else if (i < weeklist.size()) {
                            ret = String.format("%d.%d", weeklist.get(i).getMonth(), weeklist.get(i).getDate());
                        } else ret = " ";
                        return ret;
                    }
                });
                setChartData(lineChart, values2, values3, values4);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
///起
                button3.setTextColor(Color.argb(255,255,255,255));
                button1.setTextColor(Color.argb(255,102,96,93));
                button2.setTextColor(Color.argb(255,102,96,93));

                button3.setBackgroundColor(Color.argb(255,102,96,93));
                button1.setBackgroundColor(Color.argb(255,255,255,255));
                button2.setBackgroundColor(Color.argb(255,255,255,255));
///终

                initView();
                modify();
                lineChart.getLegend().setEnabled(true);
                Description description = new Description();
                description.setText("每月血糖（单位mmol/L）");
                description.setTextSize(15);
                description.setPosition(975f, 60f);
                description.setTextColor(Color.GRAY);
                lineChart.setDescription(description);

                xAxis.setAxisMinimum(0f);//设置x轴的最小值
                xAxis.setAxisMaximum(31f);//设置最大值
                xAxis.setLabelCount(31);//绘制七个标签

                List<Entry> values5 = new ArrayList<>();
                List<Entry> values6 = new ArrayList<>();
                List<Entry> values7 = new ArrayList<>();

                final List<DailyBG> monthlist = DailyBG.monthList();
///起
                int count = 0;
                mean = 0;
                percent = 0;
                max_kongfu = 0;
                max_fanhou = 0;
                max_shuqian = 0;
                min_kongfu = (float)monthlist.get(0).getBG(1);
                min_fanhou = (float)monthlist.get(0).getBG(2);
                min_shuqian = (float)monthlist.get(0).getBG(3);

               for (int i = 0; i < monthlist.size(); i++) {
                    DailyBG dailyBG = monthlist.get(i);
                    for (int j = 1; j < 4; j++) {
                        if((float) dailyBG.getBG(j)!=0)
                            switch (j) {
                                case 1:
                                    values5.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_kongfu<(float) dailyBG.getBG(j))
                                        max_kongfu = (float) dailyBG.getBG(j);
                                    if(min_kongfu>(float) dailyBG.getBG(j))
                                        min_kongfu = (float) dailyBG.getBG(j);
                                    mean = mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                                case 2:
                                    values6.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_fanhou<(float) dailyBG.getBG(j))
                                        max_fanhou = (float) dailyBG.getBG(j);
                                    if(min_fanhou>(float) dailyBG.getBG(j))
                                        min_fanhou = (float) dailyBG.getBG(j);
                                    mean = mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                                case 3:
                                    values7.add(new Entry(i + 1, (float) dailyBG.getBG(j)));
                                    if(max_shuqian<(float) dailyBG.getBG(j))
                                        max_shuqian = (float) dailyBG.getBG(j);
                                    if(min_shuqian>(float) dailyBG.getBG(j))
                                        min_shuqian = (float) dailyBG.getBG(j);
                                    mean= mean +(float) dailyBG.getBG(j);
                                    if((float) dailyBG.getBG(j)>4.4 && (float) dailyBG.getBG(j)<10.0)
                                        percent++;
                                    count++;
                                    break;
                            }
                    }
                }

                mean = mean/count;
                mean = (float)(Math.round(mean*100))/100;
                percent = percent/count;
                percent = (float)(Math.round(percent*100))/100;
                max_kongfu = (float)(Math.round(max_kongfu*100))/100;
                min_kongfu = (float)(Math.round(min_kongfu*100))/100;

                max_fanhou = (float)(Math.round(max_fanhou*100))/100;
                min_fanhou = (float)(Math.round(min_fanhou*100))/100;

                max_shuqian = (float)(Math.round(max_shuqian*100))/100;
                min_shuqian = (float)(Math.round(min_shuqian*100))/100;

                textView_daytext = (TextView)findViewById(R.id.suger_daytext);
                textView_daytext.setText("本月血糖分析");
                settext();
                textView_max_fanhou.setTextSize(15);
                textView_min_fanhou.setTextSize(15);
                textView_max_kongfu.setTextSize(15);
                textView_min_kongfu.setTextSize(15);
                textView_max_shuiqian.setTextSize(15);
                textView_min_shuiqian.setTextSize(15);

                textView_max_fanhou.setText("饭后："+max_fanhou);
                textView_max_kongfu.setText("空腹："+max_kongfu);
                textView_max_shuiqian.setText("睡前："+max_shuqian);
                textView_min_fanhou.setText("饭后："+min_fanhou);
                textView_min_kongfu.setText("空腹："+min_kongfu);
                textView_min_shuiqian.setText("睡前："+min_shuqian);
                textView_mean.setText(""+mean);
                textView_percent.setText(""+percent);
///终

                lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float v, AxisBase axisBase) {
                        String ret = " ";
                        int i = (int) v;
                        i--;
                        if(i<0) ret =" ";
                        if (i < monthlist.size()) {
                            if ((i % 5 == 0 ) && i < monthlist.size()-1 )
                                ret = String.format("%d.%d", monthlist.get(i).getMonth(), monthlist.get(i).getDate());
                        }
                        return ret;
                    }
                });
                setChartData(lineChart, values5, values6, values7);
            }
        });
    }

    //数据处理和话折线图函数
    public static  void setChartData(LineChart lineChart,List<Entry> values1,List<Entry> values2,List<Entry> values3) {
        LineDataSet set1,set2,set3;
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            set2= (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set2.setValues(values2);
            set3= (LineDataSet) lineChart.getData().getDataSetByIndex(2);
            set3.setValues(values3);

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();

        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1= new LineDataSet(values1, "空腹血糖值");
            set1.setColor(Color.argb(179,91,155,213));
            set1.setCircleColor(Color.argb(179,91,155,213));
            set1.setValueTextColor(Color.argb(179,91,155,213));
            set1.setLineWidth(2f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.setDrawValues(false);

            set2= new LineDataSet(values1, "饭后血糖值");
            set2.setColor(Color.argb(179,237,125,49));
            set2.setCircleColor(Color.argb(179,237,125,49));
            set2.setValueTextColor(Color.argb(179,237,125,49));
            set2.setLineWidth(2f);//设置线宽
            set2.setCircleRadius(3f);//设置焦点圆心的大小
            set2.setDrawValues(false);

            set3= new LineDataSet(values1, "睡前血糖值");
            set3.setColor(Color.argb(150,89,69,61));
            set3.setCircleColor(Color.argb(150,89,69,61));
            set3.setValueTextColor(Color.argb(150,89,69,61));
            set3.setLineWidth(2f);//设置线宽
            set3.setCircleRadius(3f);//设置焦点圆心的大小
            set3.setDrawValues(false);

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
            dataSets.add(set3);
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
        }
    }

    //新建线形图，并设置初始参数
    public LineChart initView() {

        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setEnabled(true);

        //轴线参数设置
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = lineChart.getAxisLeft();

        leftAxis.setDrawZeroLine(false);
        leftAxis.setTextColor(Color.GRAY);//设置字体颜色
        leftAxis.setTextSize(10f);//设置字体
        leftAxis.setAxisMaximum(20);
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawAxisLine(false);//是否绘制轴线

        //绘制健康血糖区域线
        LimitLine ylimitLine1 = new LimitLine(10.0f, "正常血糖最大值");
///改变颜色

        ylimitLine1.setLineColor(Color.argb(255,255,0,0));
        ylimitLine1.setLineWidth(2f);
        ylimitLine1.enableDashedLine(10f, 10f, 10f);
///改变颜色
        ylimitLine1.setTextColor(Color.argb(255,255,0,0));

        LimitLine ylimitLine2 = new LimitLine(4.4f, "正常血糖最小值");
///改变颜色
        ylimitLine2.setLineColor(Color.argb(255,255,0,0));
        ylimitLine2.setLineWidth(2f);
 ///改变颜色
        ylimitLine2.enableDashedLine(10f, 10f, 10f);
        ylimitLine2.setTextColor(Color.argb(255,255,0,0));

        leftAxis.addLimitLine(ylimitLine1);
        leftAxis.addLimitLine(ylimitLine2);

        //设置图例的参数
        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.GRAY);
        legend.setTextSize(10f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setWordWrapEnabled(true);

        //设置交互参数
        lineChart.setTouchEnabled(false); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true

        //动画展示
        lineChart.animateXY(1000, 1000);
        return  lineChart;
    }


    //X轴初始化函数
    public XAxis modify(){

        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(false);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(10f);//设置字体
        xAxis.setTextColor(Color.GRAY);//设置字体颜色
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
        xAxis.setTextColor(Color.GRAY);//设置轴标签的颜色
        xAxis.setTextSize(10f);//设置轴标签的大小
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setYOffset(-6);
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值

        return  xAxis;
    }

///新增函数
    public void settext(){
        textView_max_kongfu = (TextView)findViewById(R.id.suger_max_kongfu);
        textView_max_fanhou = (TextView)findViewById(R.id.suger_max_fanhou);
        textView_max_shuiqian = (TextView)findViewById(R.id.suger_max_shuiqian);
        textView_min_kongfu = (TextView)findViewById(R.id.suger_min_kongfu);
        textView_min_fanhou = (TextView)findViewById(R.id.suger_min_fanhou);
        textView_min_shuiqian = (TextView)findViewById(R.id.suger_min_shuiqian);
        textView_mean =(TextView) findViewById(R.id.suger_mean);
        textView_percent = (TextView)findViewById(R.id.suger_pass_percent);

    }
}




