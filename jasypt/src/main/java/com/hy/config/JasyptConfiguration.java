package com.hy.config;

import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfiguration {
	// 这里的名字必须是jasyptStringEncryptor,不能改动
    @Bean(name = "jasyptStringEncryptor")
    @ConditionalOnMissingBean
    public StringEncryptor stringEncryptor(MyEncryptablePropertyDetector propertyDetector){
        return new DefaultEncryptor(propertyDetector);
    }
    // 这里的名字必须是encryptablePropertyDetector,不能改动
    @Bean(name = "encryptablePropertyDetector")
    @ConditionalOnMissingBean
    public MyEncryptablePropertyDetector encryptablePropertyDetector() {
        return new MyEncryptablePropertyDetector();
    }
}
