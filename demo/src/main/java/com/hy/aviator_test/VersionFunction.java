package com.hy.aviator_test;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * Description: 自定义版本比较函数
 * Author: yhong
 * Date: 2024/1/8
 */
public class VersionFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "compareVersion";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject args1, AviatorObject args2) {
        String v1 = FunctionUtils.getStringValue(args1, env);
        String v2 = FunctionUtils.getStringValue(args2, env);
        return new AviatorBigInt(VersionUtil.compareVersion(v1, v2));
    }
}
