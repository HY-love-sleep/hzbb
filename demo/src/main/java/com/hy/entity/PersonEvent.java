package com.hy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description: 用户事件
 * Author: yhong
 * Date: 2024/1/31
 */
@Data
@AllArgsConstructor
public class PersonEvent {
    private Person person;
    private String addOrUpdate;
}
