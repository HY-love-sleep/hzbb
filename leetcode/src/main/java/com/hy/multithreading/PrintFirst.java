package com.hy.multithreading;

/**
 * Description:
 * Author: yhong
 * Date: 2023/12/7
 */
public class PrintFirst implements Runnable{

    @Override
    public void run() {
        System.out.println("  1  ");
    }
}
