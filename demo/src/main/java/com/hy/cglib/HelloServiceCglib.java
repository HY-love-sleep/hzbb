package com.hy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 * Author: yhong
 * Date: 2024/3/7
 */
public class HelloServiceCglib implements MethodInterceptor {
    private  Object target;
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before sysHello");
        // 执行目标方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after sysHello");
        return result;
    }
}
