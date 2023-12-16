package com.hy.abs_factory;

/**
 * Description: 测试类
 * Author: yhong
 * Date: 2023/12/16
 */
public class Test {
    public static void main(String[] args) {
        WindowsFactory windowsFactory = new WindowsFactory();
        OperatingSystem windowsSystem = windowsFactory.createSystem();
        Application windowsApplication = windowsFactory.createApplication();
        windowsSystem.run();
        windowsApplication.open();
    }
}
