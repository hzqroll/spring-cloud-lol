package com.roll.demo2.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author haozq
 * Date: 2018/7/16 下午5:36
 */
public class UserCommand<T> extends HystrixCommand<T> {

	UserCommand(String groupKey, String commandKey) {
		super(Setter.
				withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey)).
				andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter().
								withExecutionTimeoutInMilliseconds(100)));
	}

	@Override
	protected T run() {
		// 具体业务逻辑 log
		return null;
	}

	@Override
	protected T getFallback() {
		//run 方法抛出异常的时候调用此方法 log
		return null;
	}
}
