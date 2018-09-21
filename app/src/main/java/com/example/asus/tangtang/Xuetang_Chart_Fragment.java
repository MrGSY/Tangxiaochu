package com.example.asus.tangtang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

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

public class Xuetang_Chart_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xuetang_chart_fragment, container, false);
    }

    public void paint(){
        LineChart lineChart = (LineChart) getActivity().findViewById(R.id.lineChart);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.rgb(91,62,69));
        lineChart.setDrawBorders(false);
        lineChart.setEnabled(true);
        lineChart.getLegend().setEnabled(false);
        Description description = new Description();
        description.setText("每日血糖值（单位mmol/L）");
        description.setTextSize(15);
        description.setPosition(1000f,60f);
        description.setTextColor(Color.rgb(255,255,255));
        lineChart.setDescription(description);

        List<Entry> values1 = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        int year, month, date;
        year = today.get(Calendar.YEAR);
        month = today.get((Calendar.MONTH)) + 1;
        date = today.get(Calendar.DATE);
        DailyBG todayBG = DailyBG.getDailyBG(year, month, date);
        for (int i = 1; i < 8; i++) {
            if((float) todayBG.getBG(i)!=0)
                values1.add((new Entry(i, (float) todayBG.getBG(i))));
        }

        //设置坐标轴参数
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        YAxis leftAxis = lineChart.getAxisLeft();

        leftAxis.setDrawZeroLine(false);
        leftAxis.setTextColor(Color.rgb(255,255,255));//设置字体颜 .
        leftAxis.setTextSize(10f);//设置字体
        leftAxis.setAxisMaximum(20);
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawAxisLine(false);//是否绘制轴线

        //绘制健康血糖区域线
        LimitLine ylimitLine1 = new LimitLine(10.0f, "正常血糖最大值");
        ylimitLine1.setLineColor(Color.rgb(255,255,255));
        ylimitLine1.setLineWidth(2f);
        ylimitLine1.enableDashedLine(10f, 10f, 10f);
        ylimitLine1.setTextColor(Color.rgb(255,255,255));

        LimitLine ylimitLine2 = new LimitLine(4.4f, "正常血糖最小值");
        ylimitLine2.setLineColor(Color.rgb(255,255,255));
        ylimitLine2.setLineWidth(2f);
        ylimitLine2.enableDashedLine(10f, 10f, 10f);
        ylimitLine2.setTextColor(Color.rgb(255,255,255));

        leftAxis.addLimitLine(ylimitLine1);
        leftAxis.addLimitLine(ylimitLine2);

        //设置图例参数
        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.rgb(255,255,255));
        legend.setTextSize(10f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setWordWrapEnabled(true);

        lineChart.setTouchEnabled(false); // 设置是否可以触摸
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.animateXY(1000, 1000);


        //设置X轴参数
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(false);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(10f);//设置字体
        xAxis.setTextColor(Color.rgb(255,255,255));//设置字体颜色
        xAxis.setAxisMinimum(0f);//设置x轴的最小值
        xAxis.setAxisMaximum(8f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setLabelCount(7);//绘制七个标签
        xAxis.setTextColor(Color.rgb(255,255,255));//设置轴标签的颜色
        xAxis.setTextSize(10f);//设置轴标签的大小
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setYOffset(-6);

        //设置横坐标显示内容
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

        //画出折线图
        LineDataSet set1;
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();

        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "空腹血糖值（单位：mmol/L)\t\t");
            set1.setColor(Color.rgb(255,255,255));
            set1.setCircleColor(Color.rgb(255,255,255));
            set1.setValueTextColor(Color.rgb(255,255,255));
            set1.setLineWidth(2f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.setDrawValues(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        paint();
        RelativeLayout relativeLayout=(RelativeLayout)getActivity().findViewById(R.id.xuetangchart_formore) ;
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),XuetangChart.class);
                startActivity(intent);
            }
        });

    }
}
