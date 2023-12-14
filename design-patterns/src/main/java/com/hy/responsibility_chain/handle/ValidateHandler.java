package com.hy.responsibility_chain.handle;

import com.hy.responsibility_chain.entity.Member;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 校验节点
 * Author: yhong
 * Date: 2023/12/4
 */
public class ValidateHandler<T> extends Handler<T>{
    @Override
    public void doHandler(Member member) {
        if (StringUtils.isEmpty(member.getUserName()) ||
                StringUtils.isEmpty(member.getPasswd())) {
            // todo:结合logback
            System.out.println("信息不能为空！");
            return;
        }
        System.out.println("信息不为空");
        // 如果不是为节点， 调用下一节点
        if (null != next) {
            next.doHandler(member);
        }
    }
}
