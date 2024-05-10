package com.hy.ioctest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Description: 自定义bean后置处理器
 *
 * @author: yhong
 * Date: 2024/5/10
 */
public class MyBeanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition a = configurableListableBeanFactory.getBeanDefinition("a");
        System.out.println("我是postProcessBeanFactory::" + a.getBeanClassName());
    }
}
