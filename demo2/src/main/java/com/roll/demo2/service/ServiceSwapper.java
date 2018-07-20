package com.roll.demo2.service;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

/**
 * 执行传入的方法
 *
 * @author haozq
 * Date: 2018/7/17 上午10:10
 */
@Service
public class ServiceSwapper<T> {

	private String groupKey;
	private String commandKey;
	private Class service;
	private String method;

	public T getUser(long uid) {
		return (new UserCommand<T>(groupKey, commandKey) {
			@Override
			protected T run() {
				try {
					Object[] args = new Object[10];
					Method m = service.getClass().getMethod(method);
					Object invoke = m.invoke(service, args);
					return (T) invoke;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return super.run();
			}

			@Override
			protected T getFallback() {
				return super.getFallback();
			}
		}).execute();
	}

}
