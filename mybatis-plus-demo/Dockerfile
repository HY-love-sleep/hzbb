# 使用官方的 Java 8 运行时作为基础镜像
FROM openjdk:8-jdk-alpine

# 设置应用的工作目录
WORKDIR /app

# 将构建好的 JAR 包复制到镜像中【修改jar包名称为具体项目】
COPY target/mybatis-plus-demo-1.0-SNAPSHOT.jar /app/mybatis-plus-demo-1.0-SNAPSHOT.jar

# 暴露应用端口
EXPOSE 8080

# 定义环境变量
ENV SPRING_PROFILES_ACTIVE=prod

# 运行应用【修改含特定的 JVM 参数】
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-XX:MaxPermSize=256m", "-jar", "mybatis-plus-demo-1.0-SNAPSHOT.jar"]