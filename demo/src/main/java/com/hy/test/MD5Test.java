package com.hy.test;

import cn.hutool.crypto.digest.MD5;

/**
 * Description:
 * Author: yhong
 * Date: 2024/4/16
 */
public class MD5Test {
    private static String getMD5Key(String key) {
        return MD5.create().digestHex16(key);
    }

    public static void main(String[] args) {
        String idToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImI3MzdlZmZlLTg1NWUtNDBmMC1iMTNhLTNiMzUxMGY3ZWIyNSJ9.eyJzdWIiOiJ0ZW5hbnQiLCJhdWQiOiI5IiwibmJmIjoxNzEzMzM3Njg5LCJhenAiOiI5IiwiZXhwIjoxNzE5ODE3Njg5LCJpYXQiOjE3MTMzMzc2ODksImp0aSI6ImIwZjgwOGFkLTE0YWMtNGM2Ni1iM2E5LTY4NTQzZDA3OTI5OCJ9.GmxlfqOg83vbef-o7v5PXnIPUmmzL705f6mYbEVbkfT5fg1lAwHdA2Bbs2KgBJcnF5vkkaZ80kOSo9fL1Jioep4zYiYMRwlyWo-zprywANIPcCsB8EBi7CshlDfDLCZEdb_U3vCyxdI7NtXzC7GYA9Gb0UR3KRk8LU9jF3bkdLUt-txVTSt1bf0UxLZsLcQmcEBD26JflEyt8JYmsbpNGO_h1LaydKymNavR7dAskweDU9zNTxkjTxbi-QD4JcFYDh5quTZcsvu8EfDd7niNaja6iD6yHn-udLmTxLdu-CTvlXqC5iKWB9h5WBCc2kxI-XxnI8Il0KnEm0K0wMNDRw";

        System.out.println(getMD5Key(idToken));
    }
}
