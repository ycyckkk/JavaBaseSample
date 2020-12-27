package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/27 11:16
 */
public class ChooseSort1 {
    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83};
        sort(array);
        System.out.println(JSON.toJSONString(array));
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int small = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[small] > array[j]) {
                    small = j;
                }
            }
            int temp = array[small];
            array[small] = array[i];
            array[i] = temp;
        }
    }
}
