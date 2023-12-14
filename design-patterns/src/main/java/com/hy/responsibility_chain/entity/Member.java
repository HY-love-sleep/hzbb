package com.hy.responsibility_chain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 用户实体类
 * Author: yhong
 * Date: 2023/12/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Integer id;
    private String userName;
    private String passwd;

    public Member(String name, String passwd) {
        this.userName = name;
        this.passwd = passwd;
    }
}
