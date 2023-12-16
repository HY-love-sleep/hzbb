package com.hy.abs_factory;

/**
 * Description: Linux工厂
 * Author: yhong
 * Date: 2023/12/16
 */
public class LinuxFactory extends SoftwareFactory{

    @Override
    public OperatingSystem createSystem() {
        return new LinuxSystem();
    }

    @Override
    public Application createApplication() {
        return new ExcelApplication();
    }
}
