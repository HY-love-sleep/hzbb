package com.hy.cglib;

/**
 * Description:
 * Author: yhong
 * Date: 2024/3/7
 */
public class Test {
    public static void main(String[] args) {
        HelloService target = new HelloService();
        HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
        HelloService instance = (HelloService) helloServiceCglib.getInstance(target);
        instance.sayHello("hongy25");
    }
}
