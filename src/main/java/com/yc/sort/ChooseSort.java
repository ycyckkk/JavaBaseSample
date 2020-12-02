package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * 12 + 10 +
 * @Author yucheng
 * @Date 2020/12/2 8:57
 */
public class ChooseSort extends Sort {
    public static void main(String[] args) {
        ChooseSort chooseSort = new ChooseSort();
        int[] array = new int[]{
                1, 5, 8, 2, 3, 4, 6, 5, 22, 33, 55, 66
        };
        chooseSort.sort1(array);
        System.out.println(JSON.toJSONString(array));

    }

    //外层从第一个到最后一个-1l
    //内层从第i个，到最后一个
    //比较找到最小的
    void sort1(int[] array) {
        System.out.println("length =" + array.length);
        //外面从0~n-1，里面从1~n，查找到最小的值
        for (int i = 0; i < array.length - 1; i++) {
            int small = i;
            for (int j = i + i; j < array.length; j++) {
                if (less(array[j], array[small])) {
                    small = j;
                }
            }
            swap(small, i, array);
        }
    }
}
