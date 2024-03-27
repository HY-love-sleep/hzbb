package com.hy.test;

class InnerObject {
    int value;

    public InnerObject(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class OuterObject {
    InnerObject inner;

    public OuterObject(InnerObject inner) {
        this.inner = inner;
    }

    public InnerObject getInner() {
        return inner;
    }
}

public class Main {
    public static void modifyInnerObject(OuterObject outer) {

        // outer.getInner().setValue(100); // 修改了内部对象的值
        outer = new OuterObject(new InnerObject(100)); // 没有修改内部对象的值
    }

    public static void main(String[] args) {
        InnerObject inner = new InnerObject(50);
        OuterObject outer = new OuterObject(inner);
        
        System.out.println("Before modification: " + outer.getInner().getValue()); // 输出 50

        modifyInnerObject(outer); // 调用方法修改内部对象的值

        System.out.println("After modification: " + outer.getInner().getValue()); // 输出 100，原始对象被修改了
    }
}
