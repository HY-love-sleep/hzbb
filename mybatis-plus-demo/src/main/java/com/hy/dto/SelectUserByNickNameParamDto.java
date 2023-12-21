package com.hy.dto;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 该代码由mybatis-plus-generator-ui自动生成
 *
 * @author 
 * @since 2023-12-21
 */
@Data
@Accessors(chain = true)
public class SelectUserByNickNameParamDto {

    private String userName;

    private String nickName;

}
