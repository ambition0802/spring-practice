package com.huang.spring.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(scanBasePackages = {"com.huang.*"})
@EnableTransactionManagement
//事务管理的方式，默认是动态代理(限制@Transactional添加的方法必须是public，且不能是类内部自调用，否则事务不生效)，使用AspectJ实现则没有上述问题。
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@MapperScan({"com.huang.spring.practice"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
