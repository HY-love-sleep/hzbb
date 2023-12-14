package com.hy.responsibility_chain.test;

import com.hy.responsibility_chain.entity.Member;
import com.hy.responsibility_chain.handle.BusinessHandler;
import com.hy.responsibility_chain.handle.Handler;
import com.hy.responsibility_chain.handle.LoginHandler;
import com.hy.responsibility_chain.handle.ValidateHandler;

/**
 * Description: 测试类
 * Author: yhong
 * Date: 2023/12/4
 */
public class Test {
    public static void main(String[] args) {
        Member member = new Member("hzbb", "971122");
        Handler.Builder builder = new Handler.Builder();
        builder.addHandler(new ValidateHandler())
                .addHandler(new LoginHandler())
                .addHandler(new BusinessHandler());
        builder.build().doHandler(member);
    }

}
