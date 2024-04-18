package com.hy.interceptor;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.hy.config.ApiContext;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 租户插件
 * Author: yhong
 * Date: 2024/4/18
 */
@RequiredArgsConstructor
public class TenantIdInterceptor implements TenantLineHandler {

    private final ApiContext apiContext;

    @Override
    public Expression getTenantId() {
        Long currentTenantId = apiContext.getCurrentTenantId();
        return new LongValue(currentTenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName);
    }

    @Override
    public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
        return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
    }
}
