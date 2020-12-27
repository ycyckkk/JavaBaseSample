package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/27 11:10
 */
public class BuddleSort1 {
    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83};
        buddleSort(array);
        System.out.println(JSON.toJSONString(array));
    }

    public static void buddleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
