package com.example.asus.tangtang;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.utils.ViewPortHandler;

import org.w3c.dom.Text;

import java.util.List;

public class FoodListItemAdapter extends ArrayAdapter<FoodListItem> {
    private int resourceId;
    public FoodListItemAdapter(Context context, int textViewResourceId, List<FoodListItem> objects){
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //利用convertView和ViewHolder优化
        FoodListItem foodListItem=getItem(position);
        View view;
        ViewHolder viewHolder=null;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.FoodImage=(ImageView)view.findViewById(R.id.FoodImage);
            viewHolder.FoodName=(TextView)view.findViewById(R.id.FoodName);
            viewHolder.FoodInfo=(TextView)view.findViewById(R.id.FoodInfo);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.FoodImage.setImageResource(foodListItem.getImageId());
        viewHolder.FoodName.setText(foodListItem.getName());
        viewHolder.FoodInfo.setText(foodListItem.getInfo());
        return view;
    }

    class ViewHolder{
        ImageView FoodImage;
        TextView FoodName;
        TextView FoodInfo;
    }
}
