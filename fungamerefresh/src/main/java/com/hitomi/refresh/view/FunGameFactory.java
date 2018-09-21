package com.hitomi.refresh.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Hitomis on 2016/3/10.
 * email:196425254@qq.com
 */
public class FunGameFactory {

    // Refused to use enum

    //static final int HITBLOCK = 0;

    static final int BATTLECITY = 1;

    static FunGameView createFunGameView(Context context, AttributeSet attributeSet, int type) {
        FunGameView funGameView = null;
        funGameView = new BattleCityView(context, attributeSet);
        return funGameView;
    }
}
