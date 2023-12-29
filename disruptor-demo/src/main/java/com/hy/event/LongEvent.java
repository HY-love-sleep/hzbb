package com.hy.event;


/**
 * Description: 整型事件
 * Author: yhong
 * Date: 2023/12/29
 */

public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }

}
