package com.hy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 标记需要上报异常的方法
 *
 * @author: yhong
 * Date: 2024/11/12
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogException {
    String appName() default "数据安全保障平台";
}
