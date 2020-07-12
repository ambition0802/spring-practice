package com.huang.spring.practice.user.controller;

import com.huang.spring.practice.user.dao.UserMapper;
import com.huang.spring.practice.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller层
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserInfo/{id}")
    public User getUserInfo(@PathVariable("id") Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
