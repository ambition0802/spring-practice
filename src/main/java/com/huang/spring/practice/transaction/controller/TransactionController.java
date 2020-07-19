package com.huang.spring.practice.transaction.controller;

import com.huang.spring.practice.transaction.service.TransactionService;
import com.huang.spring.practice.user.dao.UserMapper;
import com.huang.spring.practice.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionService transactionService;

    /**
     * 测试Spring事务
     * 插入一个新user之后，故意抛出运行时异常，Spring事务会回滚，插入user失败
     */
    @Transactional
    @PostMapping("/testTransactionThrowExcep")
    public User testTransactionThrowExcep() {
        User user = new User();
        user.setName("小李");
        user.setAge((short) 13);
        user.setCity("北京");
        userMapper.insert(user);

        throw new RuntimeException("故意抛出一个运行时异常");
    }

    /**
     * 测试Spring事务
     * 成功插入user
     */
    @Transactional
    @PostMapping("/testTransactionNoExcep")
    public User testTransactionNoExcep() {
        User user = new User();
        user.setName("小李");
        user.setAge((short) 13);
        user.setCity("北京");
        userMapper.insert(user);
        return user;
    }

    @PostMapping("/updateUserAgeById/{userId}/{age}")
    public void updateUserAgeById(@PathVariable("userId") long userId, @PathVariable("age") short age) {
        transactionService.updateUserAgeById(userId, age);
    }

    @PostMapping("/testTransactionModifyDto")
    public User testTransactionModifyDto() {

        transactionService.testTransactionModifyDto();

        User user2 = userMapper.selectByPrimaryKey(4L);
        System.out.println("在事务外，从DB查询id为4的用户，然后打印他的age  : " + user2.getAge());

        return user2;
    }



}
