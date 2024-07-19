package com.hy.crypto.factory;

import com.hy.crypto.EncryptionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Description: 获取具体的加密实现类
 *
 * @author: yhong
 * Date: 2024/7/19
 */
@Component
public class EncryptionStrategyFactory {

    private final Map<String, EncryptionStrategy> strategies;

    @Autowired
    public EncryptionStrategyFactory(List<EncryptionStrategy> encryptionStrategies) {
        strategies = new HashMap<>();
        for (EncryptionStrategy strategy : encryptionStrategies) {
            String algorithmName = strategy.support();
            strategies.put(algorithmName, strategy);
        }
    }

    public EncryptionStrategy getStrategy(String algorithmName) {
        return strategies.get(algorithmName);
    }
}
