package com.yc.base;

/**
 * 学习hashMap
 * 1）位运算，在java中 >> 表示右移 若该数为正，则高位补0，若为负数，高位补1;<<相反;
 *                   >>>表示无符号右移，也叫逻辑右移。不管数字是正数还是负数，高位都是补0
 *
 *
 * @Author yucheng
 * @Date 2020/12/12 16:54
 */
public class BitwiseTest {
    public static void main(String[] args) {
        System.out.println(Long.parseLong("0000000000001010",2));
        System.out.println(Long.toBinaryString(-20));
        System.out.println(Long.toBinaryString(-20 >> 2));
    }
}
