package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/26 11:54
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int[] array = new int[]{72, 6, 57, 88, 60, 42, 83};
        quickSort(array, 0, array.length - 1);
        System.out.println(JSON.toJSONString(array));
    }


    public static void quickSort(int[] array, int begin, int end) {
        System.out.println("begin=" + begin + "," + "end=" + end);
        if (begin < end) {
            int i = begin;
            int j = end;
            int key = array[begin];
            System.out.println("key =" + key);
            while (i < j) {
                while (i < j && key < array[j]) {
                    j--;
                }
                if (i < j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    System.out.println(JSON.toJSONString(array));
                    i++;
                }

                while (i < j && array[i] < key) {
                    i++;
                }
                if (i < j) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    System.out.println(JSON.toJSONString(array));
                    j--;

                }
            }
            quickSort(array, begin, i - 1);
            quickSort(array, i + 1, end);
        }
    }
}
