package com.yc.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 什么是字节码技术
 *
 * @Author yucheng
 * @Date 2020/12/11 11:01
 */
public class CglibProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return object;
    }

}
