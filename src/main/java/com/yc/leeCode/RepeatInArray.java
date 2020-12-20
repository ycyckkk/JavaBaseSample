package com.yc.leeCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * @Author yucheng
 * @Date 2020/12/12 18:50
 */
public class RepeatInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 0, 2, 5};

        Map map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            if (null != map.get(i)) {
                
            }

        }
    }

}
