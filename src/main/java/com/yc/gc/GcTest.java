package com.yc.gc;

/**
 * 测试场景：判断将大对象的引用设置为null时，是否能达到垃圾回收的效果
 * 测试条件：-Xms20M -Xmx20M -verbose:gc
 * 观察是否实现垃圾回收用例
 * 1） bytes设为null，手动触发gc
 * 2） bytes设为局部变量，手动触发gc
 * 3） bytes设为局部变量，手动触发gc，外部设置另外的局部变量
 * <p>
 * 1）和3）被回收，2）未被回收
 * <p>
 * 分析：
 * 1)3)方法调用链很长，需要手动设置为null
 * 2）局部变量，方法出栈后即自动回收
 *
 * @Author yucheng
 * @Date 2020/12/19 12:07
 */
public class GcTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = new byte[5 * 1024 * 1024* 2];
        bytes = null;
    }
}
