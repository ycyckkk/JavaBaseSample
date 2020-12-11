package com.yc.proxy.jdk;

import com.yc.proxy.common.UserService;
import com.yc.proxy.common.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 特点：代理类和委托类实现同一个接口，使用基础的反射方法
 * 方式：代理类实现InvocationHandler接口，并重写代理类的invoke方法，以实现增强过程处理
 * 优点：不需要硬编码
 * 缺点：基于接口进行代理
 *
 * @Author yucheng
 * @Date 2020/12/11 10:44
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.findUser();

        InvocationHandler invocationHandler = new ProxyInvocationHandler(userService);
        /**
         * ClassLoader loader,
         * Class<?>[] interfaces,
         * InvocationHandler h
         */
        UserService userService1 = (UserService) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), userService.getClass().getInterfaces(), invocationHandler);
        System.out.println(userService1.findUser());
    }
}
