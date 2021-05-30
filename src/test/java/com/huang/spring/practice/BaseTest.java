package com.huang.spring.practice;

import com.yellowstar.data.loader.DataLoader;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yellowstar
 * @version 1.0
 * @description  单元测试基类，其他单元测试的类都继承自该类，
 *               使用@Transactional+@Rollback注解，让所有单元测试方法中对数据库的操作都不落库
 * @created 2021/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public class BaseTest {

    @Autowired
    private DataLoader dataLoader;

    public void loaddata(String... caseKeys) {
        for (String caseKey : caseKeys) {
            dataLoader.loadDataByCaseKey(caseKey);
        }
    }

}
