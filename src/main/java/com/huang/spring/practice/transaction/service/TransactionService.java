package com.huang.spring.practice.transaction.service;

import cn.hutool.core.util.ObjectUtil;
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

        /**
         * 使用对象字节流的方式进行对象的深拷贝，
         * 具体实现的代码不用自己写，网上很多开源的工具包可以拿来直接用，
         * 我这里用的ObjectUtil是来自是hutool这个工具包，官网地址：https://www.hutool.cn/
         *
         * ObjectUtil.cloneByStream具体的源码很简单，实际上就是对ObjectOutputStream和ObjectIputStream的使用
         *
         * 需要注意的一点是用字节流的方式进行深拷贝的话，被拷贝的对象必须实现了Serializable接口，
         * 否则无法进行序列化、反序列化，拷贝会失败。
         */
        user = ObjectUtil.cloneByStream(user);

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
