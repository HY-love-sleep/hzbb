package com.hy.aspect;

import com.hy.annotation.EncryptSensitiveFields;
import com.hy.utils.EncryptUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
public class EncryptAspect {
    private static final Set<String> SENSITIVEFIELDS = new HashSet<>(Arrays.asList("account", "phone", "email"));

    @Around("@annotation(encryptSensitiveFields)")
    public Object encryptFields(ProceedingJoinPoint joinPoint, EncryptSensitiveFields encryptSensitiveFields) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] instanceof String && SENSITIVEFIELDS.contains(parameterNames[i])) {
                String value = (String) args[i];
                String encryptedValue = EncryptUtil.encrypt(value);
                args[i] = encryptedValue;
            } else if (args[i] instanceof Object) { // 处理实体类的情况
                Object entity = args[i];
                encryptFieldsInEntity(entity, SENSITIVEFIELDS);
            }
        }

        return joinPoint.proceed(args);
    }

    private void encryptFieldsInEntity(Object entity, Set<String> fieldNames) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (fieldNames.contains(field.getName())) {
                field.setAccessible(true);

                if (field.getType() == String.class) {
                    try {
                        String value = (String) field.get(entity);
                        String encryptedValue = EncryptUtil.encrypt(value);
                        field.set(entity, encryptedValue);
                    } catch (IllegalAccessException e) {
                        // 处理异常
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
