package com.yc.sort;

import java.util.Comparator;

/**
 * @Author yucheng
 * @Date 2020/12/2 8:58
 */
public abstract class Sort<T extends Comparable<T>> {

    abstract void sort1(int []array);

    protected void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
}
