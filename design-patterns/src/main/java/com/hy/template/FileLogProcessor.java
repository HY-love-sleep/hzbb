package com.hy.template;

/**
 * Description: 文件日志处理器
 *
 * @author: yhong
 * Date: 2024/1/16
 */
public class FileLogProcessor extends LogProcessor{

    @Override
    protected void preProcessor() {
        System.out.println("打开文件...");
    }

    @Override
    protected void postProcessor() {
        System.out.println("关闭文件...");
    }
}
