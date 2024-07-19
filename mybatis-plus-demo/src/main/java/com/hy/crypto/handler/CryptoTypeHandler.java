package com.hy.crypto.handler;

import com.hy.crypto.EncryptionStrategy;
import com.hy.crypto.factory.EncryptionStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Description: 自定义加解密Typehandler
 *
 * @author: yhong
 * Date: 2024/7/19
 */
@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String.class)
public class CryptoTypeHandler extends BaseTypeHandler<String> {
    private final EncryptionStrategyFactory factory;
    private final EncryptionStrategy algorithm;

    @Autowired
    public CryptoTypeHandler(EncryptionStrategyFactory factory, String algorithmName) {
        this.factory = factory;
        this.algorithm = Optional.ofNullable(factory.getStrategy(algorithmName))
                .orElseThrow(() -> new IllegalArgumentException("No encryption strategy found for algorithm: " + algorithmName));
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            String encryptedValue = algorithm.encrypt(parameter);
            ps.setString(i, encryptedValue);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String encryptedValue = rs.getString(columnName);
        if (shouldBeDecrypted(columnName)) {
            return decryptIfNotNull(encryptedValue);
        }
        return encryptedValue;

    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String encryptedValue = rs.getString(columnIndex);
        if (shouldBeDecrypted(columnIndex)) {
            return decryptIfNotNull(encryptedValue);
        }
        return encryptedValue;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String encryptedValue = cs.getString(columnIndex);
        if (shouldBeDecrypted(columnIndex)) {
            return decryptIfNotNull(encryptedValue);
        }
        return encryptedValue;
    }

    private String decryptIfNotNull(String encryptedValue) {
        if (encryptedValue != null) {
            return algorithm.decrypt(encryptedValue);
        }
        log.info("结果为空，无需解密");
        return null;
    }

    private Boolean shouldBeDecrypted(String columnName) {
        if (StringUtils.equals(columnName, "username") || StringUtils.equals(columnName, "password")
        || StringUtils.equals(columnName, "phone") || StringUtils.equals(columnName, "email")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean shouldBeDecrypted(int columnIndex) {
        if (columnIndex == 1 || columnIndex == 2 || columnIndex == 4 || columnIndex == 8) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
