package com.example.dblibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortList<E> {
    public static final int TYPE_ASCEND = 0;
    public static final int TYPE_DESCEND = 1;

    //重写Comparator
    public void Sort(List<E> list, final String method, final int sortWay) {
        Collections.sort(list, new Comparator() {
            public int compare(Object a, Object b) {
                int ret = 0;
                try {
                    Method m1 = ((E) a).getClass().getMethod(method, new Class[0]);
                    Method m2 = ((E) b).getClass().getMethod(method, new Class[0]);
                    Object object1 = m1.invoke(((E) a), new Object[]{});
                    Object object2 = m2.invoke(((E) b), new Object[]{});
                    if (sortWay==TYPE_DESCEND)// 倒序
                        ret = cmp(object2, object1);
                    else if (sortWay==TYPE_ASCEND)
                        // 正序
                        ret = cmp(object1, object2);

                } catch (NoSuchMethodException ne) {
                    System.out.println(ne);
                } catch (IllegalAccessException ie) {
                    System.out.println(ie);
                } catch (InvocationTargetException it) {
                    System.out.println(it);
                }
                return ret;
            }
        });
    }

    //根据不同类型定义比较函数
    private int cmp(Object a, Object b) {
        if(a instanceof String) {
            return ((String) a).compareTo((String) b);
        }
        if(a instanceof Integer) {
            return Integer.compare( (int) a, (int) b);
        }
        if(a instanceof Double) {
            return Double.compare( (double) a, (double) b);
        }
        if(a instanceof Float) {
            return Float.compare( (float) a, (float) b);
        }
        return 0;
    }
}
