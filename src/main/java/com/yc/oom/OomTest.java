package com.yc.oom;

import java.util.concurrent.Executors;

/**
 * @Author yucheng
 * @Date 2020/12/8 9:44
 */
public class OomTest {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        System.out.println(a == b);

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(1);
        Executors.newCachedThreadPool();

        String str = "hello";
        str += " world";
        System.out.println("str=" + str);

        OomTest test = new OomTest();
        String str1 = "hello";
        test.addStr(str1);
        System.out.println("str1=" + str1);

        //Autoboxing=Integer.valueOf
        //Unboxing = Integer.intValue
        //缓存池 （new Integer会创建新的对象，Integer.valueOf不会创建新对象）
        //String相同，new String时会创建新的对象，intern不会创建
        String strC = "abc";//新创建对象，并放入缓存池
        String strA = new String("abc");//新创建对象，不会放入缓存池
        String strB = new String("abc");//新创建对象，不会放入缓存池
        String strD = "abc".intern();
        System.out.println(strA == strB);
        System.out.println(strA == strC);
        System.out.println(strA == strD);
        System.out.println(strC == strD);

        int i = 1;
        //先赋值：i++
        //先运算：++i

        int j = i++;
//        System.out.println(++i);
//        System.out.println(j);

        //变量的初始化顺序，父类优先于子类，静态变量优先于非静态变量

        //浅拷贝：引用相同。深拷贝：引用不相同

        //封装，继承，多态（子类实现父类的方法，父类引用指向子类对象，运行的是子类的方法）
        //重载（方面名相同，方法参数和返回值不同）重写（访问权限父类>子类，异常类型和返回类型）


        //知道类的路径，可以获取类对象，从而访问到所有的属性  Class class1 =  Class.forName("");


        A A = new A();
        B B = new B();
        C C = new C();
        D D = new D();

        A.show(A);// A.show(A)
        A.show(B);// A.show(A)
        B.show(C);// B.show(A)  -> A.show(C)
        B.show(D); //A.show(C)

        A ba = new B();
        ba.show(C);//A.show(C)
        ba.show(D);//B.show(A) -> A.show(C)
    }

    public void addStr(String str) {
        str += " world";
    }
}

class A {
    public void show(A obj) {
        System.out.println("A.show(A)");
    }

    public void show(C obj) {
        System.out.println("A.show(C)");
    }
}

class B extends A {

    @Override
    public void show(A obj) {
        System.out.println("B.show(A)");
    }
}

class C extends B {
}

class D extends C {
}