package com.hy.dto;
import java.util.Date;
import lombok.Data;
/**
 * mapper.PersonMapper.selectUsersByphone的查询结果集，该代码由mybatis-plus-generator-ui自动生成
 *
 * @author hy
 * @since 2023-12-21
 */
@Data
public class SelectUsersByphoneResultDto {

    private Long userId;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String avatar;

    private String nickname;

    private String name;

    private String email;

    private Long deptId;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private String lockFlag;

    private String delFlag;

    private String wxOpenid;

    private String miniOpenid;

    private String qqOpenid;

    private String giteeLogin;

    private String oscId;

    private Long tenantId;

}
