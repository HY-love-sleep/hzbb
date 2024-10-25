package com.hy.template;

/**
 * Description: 数据库日志处理器
 *
 * @author: yhong
 * Date: 2024/1/16
 */
public class DatabaseLogProcessor extends LogProcessor{

    @Override
    protected void preProcessor() {
        System.out.println("连接数据库...");
    }

    @Override
    protected void postProcessor() {
        System.out.println("关闭数据库...");
    }
}
