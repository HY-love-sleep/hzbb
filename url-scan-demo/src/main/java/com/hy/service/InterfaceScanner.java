// package com.hy.service;
//
// import com.hy.entity.ExposAuthPath;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.event.ContextRefreshedEvent;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
// import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
// import org.springframework.web.util.pattern.PathPattern;
//
// import java.util.Map;
// import java.util.Objects;
// import java.util.Set;
// import java.util.regex.Pattern;
// import java.util.stream.Collectors;
//
// /**
//  * Description:
//  *
//  * @author: yhong
//  * Date: 2024/8/28
//  */
// @Component
// @Slf4j
// public class InterfaceScanner implements ApplicationListener<ContextRefreshedEvent> {
//
//     @Autowired
//     private RequestMappingHandlerMapping handlerMapping;
//
//     @Autowired
//     private ExposAuthPathService exposAuthPathService;
//
//     @Value("${server.servlet.context-path}")
//     private String contextPath;
//
//     @Override
//     public void onApplicationEvent(ContextRefreshedEvent event) {
//         log.info("================接口扫描开始====================");
//         Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
//         if (handlerMethods.isEmpty()) {
//             log.warn("=======未找到RequestMapping注解修饰的controller， 请手动导入接口模版====================");
//             return;
//         }
//
//         for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
//             try {
//                 RequestMappingInfo requestMappingInfo = entry.getKey();
//                 HandlerMethod handlerMethod = entry.getValue();
//                 String method = requestMappingInfo.getMethodsCondition().getMethods().iterator().next().name();
//                 Set<PathPattern> pathPatterns = requestMappingInfo.getPathPatternsCondition().getPatterns();
//                 Set<String> patterns = pathPatterns.stream().map(PathPattern::getPatternString).collect(Collectors.toSet());
//                 String path = patterns.iterator().next();
//                 Boolean variable = hasPathVariable(path);
//                 String name = Objects.requireNonNull(handlerMethod.getMethodAnnotation(RequestMapping.class)).name();
//
//                 ExposAuthPath exposAuthPath = new ExposAuthPath();
//                 exposAuthPath.setMethod(method);
//                 exposAuthPath.setPath(contextPath + path);
//                 exposAuthPath.setContextPath(contextPath);
//                 exposAuthPath.setVariable(variable);
//                 exposAuthPath.setName(name);
//                 // 程序启动时填写默认值
//                 exposAuthPath.setCreateUser(1L);
//                 exposAuthPath.setUpdateUser(1L);
//                 exposAuthPathService.saveOrUpdate(exposAuthPath);
//             } catch (Exception e) {
//                 log.error("接口解析失败， RequestMappingInfo：{}， HandlerMethod：{}", entry.getKey(), entry.getValue(), e);
//             }
//         }
//     }
//
//     /**
//      * 判断是否有路径参数
//      * @param path
//      * @return
//      */
//     private Boolean hasPathVariable(String path) {
//         // 使用正则表达式判断路径中是否包含路径变量
//         return Pattern.compile("\\{[^/]+\\}").matcher(path).find();
//     }
// }