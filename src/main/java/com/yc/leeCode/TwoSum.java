package com.yc.leeCode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入指定数组和目标值，返回指定下标
 * 来源：面试和leetCode
 * 目的空间换时间，用map来去重
 * 注意点：顺序问题
 * @Author yucheng
 * @Date 2020/12/8 17:05
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int source = target - nums[i];
            if (map.containsKey(source)) {
                a[1] = i;
                a[0] = map.get(source);
            }
            map.put(nums[i], i);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 10};
        int target = 6;
        System.out.println(JSON.toJSONString(twoSum(a, target)));
    }
}

