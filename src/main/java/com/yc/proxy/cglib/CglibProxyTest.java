package com.yc.proxy.cglib;

import com.yc.proxy.common.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * 引入cglib的jar包
 * 实现InterceptorHandler
 * 新建增强剂
 * 设置父类，和回调
 * 创建代理类
 * <p>
 * 特点：代理类将被委托类作为父类，最终创建一个委托方法；委托方法和方法签名相同，由委托方法进行调用；代理方法会拦截代理委托方法，判断是否实现Interceptor接口
 * 优点：运行时增强接口和类
 * 缺点：无法对final类进行增强
 * The method underlying all into an array, a method called directly by the array index
 *
 * @Author yucheng
 * @Date 2020/12/11 11:16
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        Enhancer enhancer = new Enhancer();//增强剂
        enhancer.setSuperclass(userServiceImpl.getClass()); //父
        enhancer.setCallback(cglibProxy);//回调

        UserServiceImpl newUserServiceImpl = (UserServiceImpl) enhancer.create();
        System.out.println(newUserServiceImpl.findUser());
    }
}
