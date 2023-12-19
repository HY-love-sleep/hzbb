package com.hy.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * Description: 用户类数据传输对象
 * Author: yhong
 * Date: 2023/12/15
 */
@Data
public class UserDTO {
    private Long id;
    private String userNo;
    private String nickname;
    private String email;
    private String phone;
    private Integer gender;
    private Date birthday;
}
