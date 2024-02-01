package com.hy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * Description: 泛型事件
 * Author: yhong
 * Date: 2024/1/31
 */
@Data
@AllArgsConstructor
public class BaseEvent<T> implements ResolvableTypeProvider {
    private T event;
    private String addOrUpdate;

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getEvent()));
    }
}
