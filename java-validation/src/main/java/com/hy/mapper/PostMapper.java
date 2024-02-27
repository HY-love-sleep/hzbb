package com.hy.mapper;

import com.hy.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hongy
 * @since 2024-02-27
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
