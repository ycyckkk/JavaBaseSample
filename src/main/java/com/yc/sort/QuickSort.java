package com.yc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author yucheng
 * @Date 2020/12/2 12:01
 */
public class QuickSort extends Sort {
    public static void main(String[] args) {
        int[] array = new int[]{
                72, 6, 57, 88, 60, 42, 83
//                1, 4, 8, 2, 3, 5, 6, 5, 22, 33, 55, 66
//                1, 2, 8, 4, 3, 5, 6, 5, 22, 33, 55, 66
        };
        QuickSort quickSort = new QuickSort();
        quickSort.sort1(array);
        System.out.println(JSON.toJSONString(array));
    }

    void sort1(int[] array) {
        fastSort(array, 0, array.length - 1);
    }

    void fastSort(int[] array, int begin, int end) {
        System.out.println("begin =" + begin + ",end=" + end);
        //保证不碰到
        if (begin < end) {
            int key = array[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                //从后面往前，判断后面元素是否大于切分元素
                while (i < j && key < array[j]) {
                    j--;
                }
                //小于则进行替换
                if (i < j) {
                    int temp1 = array[i];
                    array[i] = array[j];
                    array[j] = temp1;
                    //首个元素已经替换
                    i++;
                }
                //从前往后，判断前面元素是否小于切分元素
                while (i < j && array[i] < key) {
                    i++;
                }
                //大于则进行替换
                if (i < j) {
                    int temp1 = array[j];
                    array[j] = array[i];
                    array[i] = temp1;
                    j--;
                }
            }
//            array[i] = key;//中间元素
//            System.out.println("key =" + key);
            fastSort(array, begin, i - 1);//以切分元素为节点重新进行递归排序
            fastSort(array, i + 1, end);
        }
    }
}
