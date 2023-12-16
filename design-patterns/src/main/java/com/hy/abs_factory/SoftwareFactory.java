package com.hy.abs_factory;

/**
 * Description: 抽象工厂
 * Author: yhong
 * Date: 2023/12/16
 */
public abstract class SoftwareFactory {
    public abstract OperatingSystem createSystem();
    public abstract Application createApplication();
}
