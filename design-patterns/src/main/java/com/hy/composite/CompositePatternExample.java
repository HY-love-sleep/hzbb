package com.hy.composite;

/**
 * Description:
 * 在这个例子中，FileSystemComponent是组合模式的组件接口，File是叶子结点，Directory是容器结点；
 * 通过这些类， 可以构造一个具有层次结构的文件系统；
 * Author: yhong
 * Date: 2024/1/4
 */
public class CompositePatternExample {
    public static void main(String[] args) {
        // 创建文件和文件夹；
        File file1 = new File("123.txt");
        File file2 = new File("abc.txt");

        Directory subDirectory = new Directory("SubDirectory");
        subDirectory.addComponents(file1);
        subDirectory.addComponents(file2);

        Directory root = new Directory("Root");
        root.addComponents(subDirectory);

        root.displayInfo();
    }
}
