package com.hy.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserInfoByAuthCode implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String uid;
}