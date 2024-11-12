package com.hy.service.impl;

import com.hy.entity.ExposExceptionLog;
import com.hy.mapper.ExposExceptionLogMapper;
import com.hy.service.ExposExceptionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhong
 * @since 2024-11-07
 */
@Service
@Slf4j
public class ExposExceptionLogServiceImpl extends ServiceImpl<ExposExceptionLogMapper, ExposExceptionLog> implements ExposExceptionLogService {

    // @Resource
    // private RestTemplate restTemplate;
    @Override
    public void uploadExLog(ExposExceptionLog exLog) {
        // // 构建请求 URL
        // String url = "http://xxx:xx/dgpc/api/error/log/add";
        //
        // // 设置请求头
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        //
        // // 创建 HTTP 请求实体
        // HttpEntity<ExposExceptionLog> requestEntity = new HttpEntity<>(exLog, headers);
        //
        // // 发送 POST 请求
        // ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        //
        // // 处理响应
        // if (response.getStatusCode().is2xxSuccessful()) {
        //     log.info("异常日志上传成功: {}", response.getBody());
        // } else {
        //     log.error("异常日志上传失败: {}", response.getBody());
        // }
    }
}
