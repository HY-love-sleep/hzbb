package com.hy.abs_factory;

/**
 * Description: windows工厂
 * Author: yhong
 * Date: 2023/12/16
 */
public class WindowsFactory extends SoftwareFactory{

    @Override
    public OperatingSystem createSystem() {
        return new WindowsSystem();
    }

    @Override
    public Application createApplication() {
        return new WordApplication();
    }
}
