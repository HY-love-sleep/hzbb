package com.hy.responsibility_chain.handle;

import com.hy.responsibility_chain.entity.Member;

/**
 * Description: 业务节点
 * Author: yhong
 * Date: 2023/12/4
 */
public class BusinessHandler<T> extends Handler<T> {
    @Override
    public void doHandler(Member member) {
        System.out.println("开始执行业务逻辑...");
    }
}
