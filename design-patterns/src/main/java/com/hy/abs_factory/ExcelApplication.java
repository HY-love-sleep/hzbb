package com.hy.abs_factory;

/**
 * Description: Excel程序
 * Author: yhong
 * Date: 2023/12/16
 */
public class ExcelApplication implements Application{

    @Override
    public void open() {
        System.out.println("open excel application");
    }
}
