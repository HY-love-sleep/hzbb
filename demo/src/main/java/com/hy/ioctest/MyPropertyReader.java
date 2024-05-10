package com.hy.ioctest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertyReader {
    public static Properties readPropertyForMe(String path){
        Properties properties = new Properties();
        try(InputStream sin = MyPropertyReader.class.getResourceAsStream(path)){
            properties.load(sin);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("读取异常...");
        }
        return properties;
    }
}