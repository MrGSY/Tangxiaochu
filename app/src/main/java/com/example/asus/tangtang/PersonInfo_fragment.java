package com.example.asus.tangtang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PersonInfo_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.personinfo_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView=(TextView)getActivity().findViewById(R.id.username_PersonInfo);
        textView.setText(UserName.getUsername_saved());

        //各项跳转事件
        Button button_setF=(Button) getActivity().findViewById(R.id.set_FoodBlackList);
        button_setF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_foodblacklist=new Intent(getActivity(), FoodBlackList.class);
                startActivity(intent_foodblacklist);
            }
        });

        Button button_setP=(Button)getActivity().findViewById(R.id.set_PersonInfo);
        button_setP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_personinfo=new Intent(getActivity(), SetPersonInfo.class);
                startActivity(intent_personinfo);
            }
        });

        Button button_setC=(Button)getActivity().findViewById(R.id.common_settings);
        button_setC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_commonsettings=new Intent(getActivity(), CommonSettings.class);
                startActivity(intent_commonsettings);
            }
        });
    }
}
