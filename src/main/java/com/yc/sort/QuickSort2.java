package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/27 11:04
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83};
        quickSort(array, 0, array.length - 1);
        System.out.println(JSON.toJSONString(array));
    }

    public static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int i = begin;
            int key = array[begin];
            int j = end;
            while (i < j && key < array[j]) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
            while (i < j && array[i] < key) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i--;
            }
            quickSort(array, begin, i - 1);
            quickSort(array, i + 1, end);
        }
    }
}
