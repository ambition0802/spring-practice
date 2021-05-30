package com.huang.spring.practice.system;

import com.huang.spring.practice.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/27
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static <T> T getBean(Class<T> clz) {
        return SpringContext.context.getBean(clz);
    }
}
