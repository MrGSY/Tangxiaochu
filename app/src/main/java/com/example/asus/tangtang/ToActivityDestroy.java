package com.example.asus.tangtang;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ToActivityDestroy {
    private static Map<String, Activity> destoryMap = new HashMap<>();
    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */
    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        if (keySet.size() > 0) {
            for (String key : keySet) {
                if (activityName.equals(key)) {
                    destoryMap.get(key).finish();
                }
            }
        }
    }
}
