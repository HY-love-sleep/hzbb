package com.hy.dynamic_datasource;

/**
 * Description: ThreadLocal实现
 * Author: yhong
 * Date: 2023/12/12
 */
public class DataSourceContextHolder {
     private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<>();

     public static void setDatasource(String datasource) {
         DATASOURCE_HOLDER.set(datasource);
     }

     public static String getDataSource() {
         return DATASOURCE_HOLDER.get();
     }

     public static void removeDataSource() {
         DATASOURCE_HOLDER.remove();
     }
}
