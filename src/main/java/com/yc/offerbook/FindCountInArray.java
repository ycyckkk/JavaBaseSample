package com.yc.offerbook;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * 数字在排序数组中出现的次数
 * 题目链接
 *
 * @Author yucheng
 * @Date 2020/12/27 11:45
 */
public class FindCountInArray {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 3, 3, 3, 4, 6};
        System.out.println(findCount(array, 3));

    }

    public static int findCount(int[] array, int k) {
        HashMap<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            if (null != map.get(temp)) {
                int count = map.get(temp);
                count++;
                map.put(temp, count);
            } else {
                map.put(temp, 1);
            }
        }
        int output = map.get(k);
        return output;
    }
}
