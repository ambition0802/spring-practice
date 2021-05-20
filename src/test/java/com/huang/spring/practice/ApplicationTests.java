package com.huang.spring.practice;

import com.huang.spring.practice.user.dao.UserMapper;
import com.huang.spring.practice.user.dto.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {

		User user = userMapper.selectByPrimaryKey(1L);
		Assert.assertEquals(18, user.getAge().intValue());
	}

}
