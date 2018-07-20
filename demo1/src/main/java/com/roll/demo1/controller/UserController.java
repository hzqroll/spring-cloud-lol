package com.roll.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roll.poseidon.api.UserService;
import com.roll.poseidon.api.pojo.User;

/**
 * Created by haozq
 * Date: 2018/6/4 下午6:02
 */
@RestController
public class UserController implements UserService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public User get(@RequestParam("uid") Long uid) {
		return (User) redisTemplate.opsForValue().get(String.valueOf(uid));
	}

	@Override
	public void add(Long uid, String name, Long score) {
		User user = new User();
		user.setId(uid);
		user.setName(name);
		user.setScore(score);
		redisTemplate.opsForValue().set(String.valueOf(uid), user);
	}
}
