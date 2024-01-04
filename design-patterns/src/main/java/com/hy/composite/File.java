package com.hy.composite;

/**
 * Description: 叶子结点
 * Author: yhong
 * Date: 2024/1/4
 */
public class File implements FileSystemComponent{
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void displayInfo() {
        System.out.println("File : " + name);
    }
}
