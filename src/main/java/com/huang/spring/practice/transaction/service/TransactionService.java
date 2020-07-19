package com.huang.spring.practice.transaction.service;

import com.huang.spring.practice.user.dao.UserMapper;
import com.huang.spring.practice.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TransactionService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User testTransactionModifyDto() {
        User user = userMapper.selectByPrimaryKey(4L);

        System.out.println("在事务中，从DB查询id为4的用户，然后打印他的age : " + user.getAge());

        user.setAge((short) 80);
        System.out.println("在事务中，将id为4的用户的age设置为80, 不执行update命令将其更新到数据库中。");


        user = userMapper.selectByPrimaryKey(4L);
        System.out.println("在事务中，从DB查询id为4的用户，然后再打印他的age  : " + user.getAge());

        return user;
    }

    public void updateUserAgeById(long userId, short age) {
        this.updateUserAgeByIdTransactional(userId, age);
    }

    @Transactional
    private void updateUserAgeByIdTransactional(long userId, short age) {
        User user = new User();
        user.setId(userId);
        user.setAge(age);
        userMapper.updateByPrimaryKeySelective(user);
        throw new RuntimeException();
    }

}
