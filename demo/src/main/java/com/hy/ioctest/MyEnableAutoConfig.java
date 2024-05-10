package com.hy.ioctest;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)   // 导入bean定义
public @interface MyEnableAutoConfig {
}