package com.yc.base;

/**
 * @Author yucheng
 * @Date 2020/12/8 16:57
 */
public class IntegerTest {
    public static void main(String[] args) {
        //类似创建字符串对象中的常量池，不过多了一个范围（-128~127）
        Integer a = 97;
        Integer b = 97;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        //超过，所以超过了取值范围
        Integer c = 197;
        Integer d = 197;
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }
}
