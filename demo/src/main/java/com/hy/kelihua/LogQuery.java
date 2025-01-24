package com.hy.kelihua;

import java.util.function.Function;
import java.util.function.Predicate;

public class LogQuery {

    // 根据日志级别过滤
    public static Function<String, Predicate<LogEntry>> byLevel = level -> log -> log.getLevel().equals(level);

    // 根据关键字过滤
    public static Function<String, Predicate<LogEntry>> containsKeyword = keyword -> log -> log.getMessage().contains(keyword);

    // 根据日期范围过滤
    public static Function<String, Function<String, Predicate<LogEntry>>> betweenDates = startDate -> endDate -> 
        log -> log.getDate().compareTo(startDate) >= 0 && log.getDate().compareTo(endDate) <= 0;

    public static void main(String[] args) {
        // 创建一些日志条目
        LogEntry log1 = new LogEntry("INFO", "System started", "2023-10-01");
        LogEntry log2 = new LogEntry("ERROR", "Database connection failed", "2023-10-02");
        LogEntry log3 = new LogEntry("WARN", "Low disk space", "2023-10-03");

        // 构建查询条件
        Predicate<LogEntry> query = byLevel.apply("ERROR")
            .and(containsKeyword.apply("connection"))
            .and(betweenDates.apply("2023-10-01").apply("2023-10-05"));

        // 应用查询条件
        System.out.println(query.test(log1)); // false
        System.out.println(query.test(log2)); // true
        System.out.println(query.test(log3)); // false
    }
}