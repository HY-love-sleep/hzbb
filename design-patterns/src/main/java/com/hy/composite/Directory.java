package com.hy.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 容器结点
 * Author: yhong
 * Date: 2024/1/4
 */
public class Directory implements FileSystemComponent{
    private String name;

    private List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponents(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void displayInfo() {
        System.out.println("Directory : " + name);
        components.forEach(FileSystemComponent::displayInfo);
    }
}
