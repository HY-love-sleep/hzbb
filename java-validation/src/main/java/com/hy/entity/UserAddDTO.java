package com.hy.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Description: UserAdd实体类
 * Author: yhong
 * Date: 2024/2/27
 */
@Data
public class UserAddDTO {

    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
