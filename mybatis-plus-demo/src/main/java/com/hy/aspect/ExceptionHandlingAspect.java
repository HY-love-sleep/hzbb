// package com.hy.aspect;
//
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationEventPublisher;
// import org.springframework.stereotype.Component;
//
// /**
//  * Description: 捕获 try-catch 块中的异常并发布事件
//  *
//  * @author: yhong
//  * Date: 2024/11/7
//  */
// @Aspect
// @Component
// public class ExceptionHandlingAspect {
//
//     @Autowired
//     private ApplicationEventPublisher publisher;
//
//     @Around("execution(* com.hy..*(..))")
//     public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//         try {
//             return joinPoint.proceed();
//         } catch (Exception ex) {
//             // 获取必要的信息
//             String appName = "YourAppName";
//             String deptId = "YourDeptId";
//             String logType = "ERROR";
//             String logLevel = "ERROR";
//             String logContent = ex.getMessage();
//             String userId = "YourUserId"; // 你可以从上下文中获取用户ID
//             String userName = "YourUserName"; // 你可以从上下文中获取用户名
//             String traceId = "YourTraceId"; // 你可以从上下文中获取跟踪ID
//
//             // 发布异常事件
//             publisher.publishEvent(new ExceptionEvent(this, appName, deptId, logType, logLevel, logContent, userId, userName, traceId));
//
//             // 重新抛出异常或返回默认值
//             throw ex;
//         }
//     }
// }
