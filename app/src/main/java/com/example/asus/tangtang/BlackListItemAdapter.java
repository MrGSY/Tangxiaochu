package com.example.asus.tangtang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BlackListItemAdapter extends ArrayAdapter<BlackListItem> {
    private int resourceId;
    public BlackListItemAdapter(Context context, int textViewResourceId, List<BlackListItem> objects){
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BlackListItem blackListItem=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }
        else {
            view=convertView;
        }
        TextView textView=(TextView)view.findViewById(R.id.foodblacklistitem_name);
        textView.setText(blackListItem.getName());
        return view;
    }
}
