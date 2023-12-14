package com.hy.responsibility_chain.handle;

import com.hy.responsibility_chain.entity.Member;

/**
 * Description: 责任链模式 处理节点类
 * Author: yhong
 * Date: 2023/12/4
 */
public abstract class Handler<T> {
    protected Handler<T> next;

    public void next(Handler<T> next) {
        this.next = next;
    }

    public abstract void doHandler(Member member);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler<T> handler) {
            if (null == this.head) {
                this.head = handler;
                this.tail = handler;
                return this;
            }
            this.tail.next = handler;
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }
}
