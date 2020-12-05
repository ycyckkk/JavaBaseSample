package com.yc.compare;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.util.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Author yucheng
 * @Date 2020/12/5 17:31
 */
public class DiffTest {
    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList();

        for (int i = 0; i < 100000; i++) {
            list1.add("" + i);
        }
        for (int i = 100000; i > 0; i--) {
            list2.add("" + i);
        }

        //1
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<String> diff = Lists.newArrayList();
        for (String str1 : list1) {
            for (String str2 : list2) {
                if (str1.equals(str2)) {
                    diff.add(str2);
                }
            }
        }
        stopwatch.stop();
        long millis = stopwatch.elapsed(MILLISECONDS);
        System.out.println("time=" + stopwatch);


        //2
        Stopwatch stopwatch1 = Stopwatch.createStarted();
        getDifferent(list1, list2);
        stopwatch1.stop();
        long millis1 = stopwatch.elapsed(MILLISECONDS);
        System.out.println("time1=" + stopwatch1);

    }

    /**
     * 比较两个集合，获取不同的元素
     * 通过将数据放入map解决效率问题
     *
     * @param collection1
     * @param collection2
     * @return
     */
    public static Collection getDifferent(Collection collection1, Collection collection2) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collection1;
        Collection min = collection2;
        //先比较大小,这样会减少后续map的if判断次数
        if (collection1.size() < collection2.size()) {
            max = collection2;
            min = collection1;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) == null) {
                csReturn.add(object);
            } else {
                map.put(object, 2);
            }
        }
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                csReturn.add(entry.getKey());
            }
        }
        return csReturn;
    }
}
