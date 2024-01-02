package com.hy.service.impl;

import com.hy.entity.Student;
import com.hy.mapper.StudentMapper;
import com.hy.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hy
 * @since 2024-01-02
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
