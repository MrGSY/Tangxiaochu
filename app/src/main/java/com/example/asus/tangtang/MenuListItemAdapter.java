package com.example.asus.tangtang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuListItemAdapter extends ArrayAdapter<MenuListItem> {
    private int resourceId;
    public MenuListItemAdapter(Context context, int textViewResourceId, List<MenuListItem> objects){
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MenuListItem menuListItem=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView itemImage=(ImageView)view.findViewById(R.id.menulistitemimage);
        TextView itemName=(TextView)view.findViewById(R.id.menulistitemcontent);
        itemImage.setImageResource(menuListItem.getItem_image());
        itemName.setText(menuListItem.getItem_name());
        return view;
    }
}
