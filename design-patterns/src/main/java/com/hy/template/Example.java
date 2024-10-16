package com.hy.template;

/**
 * Description: 测试类
 *
 * @author: yhong
 * Date: 2024/10/16
 */
public class Example {
    public static void main(String[] args) {
        LogProcessor fileProcessor = new FileLogProcessor();
        fileProcessor.execute("file : 1234", message -> {
            System.out.println("处理文件日志中， 文件日志内容：" + message);
        });
    }
}
