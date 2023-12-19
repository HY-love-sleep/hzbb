package com.hy.query;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 * Description: 用户查询条件类
 * Author: yhong
 * Date: 2023/12/15
 */
@Data
@Builder
public class UserQuery {
    @Tolerate
    public UserQuery() {}

    private Long id;
    private String userNo;
    private String nickname;
    private String phone;
    private Integer gender;
}
