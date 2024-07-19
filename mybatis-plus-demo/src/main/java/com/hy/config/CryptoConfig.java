package com.hy.config;

import com.hy.crypto.factory.EncryptionStrategyFactory;
import com.hy.crypto.handler.CryptoTypeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/7/19
 */
@Configuration
public class CryptoConfig {

    @Value("${crypto.algorithm}")
    private String algorithmName;

    @Bean
    public CryptoTypeHandler cryptoTypeHandler(EncryptionStrategyFactory factory) {
        return new CryptoTypeHandler(factory, algorithmName);
    }
}
