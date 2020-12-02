package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/2 10:26
 */
public class BuddleSort extends Sort {
    void sort1(int[] array) {
        //外层n~1
        //里层1~j
        // j>j+1时，进行互换
        for (int i = array.length-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (less(array[j+1], array[j])) {
                    swap(j+1, j, array);
                }
            }
        }
    }
    public static void main(String[] args) {
        BuddleSort buddleSort = new BuddleSort();
        int[] array = new int[]{
                1, 5, 8, 2, 3, 4, 6, 5, 22, 33, 55, 66
        };
        buddleSort.sort1(array);
        System.out.println(JSON.toJSONString(array));

    }
}
