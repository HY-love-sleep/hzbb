package com.hy.factory;

import com.hy.event.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * Description: 事件工厂， 用于创建事件，让disruptor来为我们预分配这些事件
 * Author: yhong
 * Date: 2023/12/29
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
