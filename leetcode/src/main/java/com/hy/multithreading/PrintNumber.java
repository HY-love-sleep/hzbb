package com.hy.multithreading;

/**
 * Description: 打印数字
 * Author: yhong
 * Date: 2023/12/11
 */
public class PrintNumber {
    private int x;

    public PrintNumber(int x) {
        this.x = x;
    }

    public void accept() {
        System.out.println(x);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
