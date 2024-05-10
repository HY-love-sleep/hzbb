package com.hy.ioctest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Properties;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 导入配置类
        // return new String[]{"com.hy.ioctest.config.MyConfig"};
        Properties properties = MyPropertyReader.readPropertyForMe("/MyProperty.properties");
        String strings = (String) properties.get(MyEnableAutoConfig.class.getName());

        return new String[]{strings};
    }

}