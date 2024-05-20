package com.hy.conf;

import com.hy.utils.SdkUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Description: 自动装配类
 *
 * @author: yhong
 * Date: 2024/5/15
 */
@EnableConfigurationProperties(SDKProperties.class)
public class SdkClientAutoConfiguration {
    @Bean
    public SdkUtils sdkUtils() {
        return new SdkUtils();
    }

}
