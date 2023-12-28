package com.hy.adapter;

/**
 * Description: 测试类
 * 客户端通过适配器来画一个矩形，实际上是调用了LegacyRectangle的display方法， 但通过适配器，又符合shape接口
 * Author: yhong
 * Date: 2023/12/28
 */
public class Test {
    public static void main(String[] args) {
        RectangleAdapter rectangleAdapter = new RectangleAdapter(new LegacyRectangle());
        rectangleAdapter.draw(1, 2, 3, 4);
    }
}
