package com.hy.responsibility_chain.handle;

import com.hy.responsibility_chain.entity.Member;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 登录节点
 * Author: yhong
 * Date: 2023/12/4
 */
public class LoginHandler<T> extends Handler<T> {

    @Override
    public void doHandler(Member member) {
        if (!(StringUtils.equals("hzbb", member.getUserName()) &&
                StringUtils.equals("971122", member.getPasswd()))) {
            System.out.println("账号或者密码不正确！");
            return;
        }
        System.out.println("账号密码正确");
        if (null != next) {
            next.doHandler(member);
        }
    }
}
