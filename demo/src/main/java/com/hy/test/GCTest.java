package com.hy.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/6/27
 */
public class GCTest {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];    // 这里会出现一次 Minor GC
    }
}
