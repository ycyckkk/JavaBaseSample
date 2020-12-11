package com.yc.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author yucheng
 * @Date 2020/12/11 10:36
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public ProxyInvocationHandler(Object object) {
        this.target = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        // TODO Object object = method.invoke(proxy, args);
        System.out.println("before");
        Object object = method.invoke(target, args);
        long end = System.nanoTime();
        long elapsed = end - start;
        System.out.println("after");
//        System.out.println("Executing {}" + method.getName() + "finished in = " + elapsed + "ns");
        return object;
    }
}
