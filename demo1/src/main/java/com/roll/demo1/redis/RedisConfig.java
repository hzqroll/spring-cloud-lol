package com.roll.demo1.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by haozq
 * Date: 2018/6/7 下午3:49
 */
@Configuration
public class RedisConfig {

	private Logger LOG = LoggerFactory.getLogger(RedisConfig.class);

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		jedisConnectionFactory.setHostName("122.152.218.216");
		jedisConnectionFactory.setPort(4249);
		jedisConnectionFactory.setPassword("");
		jedisConnectionFactory.setDatabase(0);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		LOG.info("create RedisTemplate success");
		return template;
	}
}
