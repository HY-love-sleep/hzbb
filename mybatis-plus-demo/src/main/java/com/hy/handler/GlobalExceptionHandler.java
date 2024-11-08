// package com.hy.handler;
//
// import org.springframework.context.ApplicationEventPublisher;
// import org.springframework.context.ApplicationEventPublisherAware;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//
// /**
//  * Description: 全局异常处理器
//  *
//  * @author: yhong
//  * Date: 2024/11/7
//  */
//
// @ControllerAdvice
// public class GlobalExceptionHandler implements ApplicationEventPublisherAware {
//
//     private ApplicationEventPublisher publisher;
//
//     @Override
//     public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//         this.publisher = applicationEventPublisher;
//     }
//
//     @ExceptionHandler(Exception.class)
//     public void handleException(Exception ex) {
//         // 获取必要的信息
//         String appName = "YourAppName";
//         String deptId = "YourDeptId";
//         String logType = "ERROR";
//         String logLevel = "ERROR";
//         String logContent = ex.getMessage();
//         String userId = "YourUserId"; // 你可以从上下文中获取用户ID
//         String userName = "YourUserName"; // 你可以从上下文中获取用户名
//         String traceId = "YourTraceId"; // 你可以从上下文中获取跟踪ID
//
//         // 发布异常事件
//         publisher.publishEvent(new ExceptionEvent(this, appName, deptId, logType, logLevel, logContent, userId, userName, traceId));
//     }
// }
