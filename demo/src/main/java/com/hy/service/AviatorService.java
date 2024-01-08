package com.hy.service;

import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

import static sun.audio.AudioDevice.device;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/8
 */
@Service
public class AviatorService {

    @Autowired
    private AviatorEvaluatorInstance instance;

    public boolean filter(String device, String version, String rule) {
        Map<String, Object> env = new HashMap<>();
        env.put("device", device);
        env.put("version", version);
        // 编译脚本
        Expression expression = instance.compile(DigestUtils.md5DigestAsHex(rule.getBytes()), rule, true);
        // 执行脚本
        return (boolean) expression.execute(env);
    }
}
