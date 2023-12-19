package com.hy.multithreading;

/**
 * Description:
 * Author: yhong
 * Date: 2023/12/7
 */
public class PrintSecond implements Runnable{

    @Override
    public void run() {
        System.out.println("  2  ");
    }
}
