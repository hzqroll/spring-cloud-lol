package com.roll.demo2.service.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author haozq
 * Date: 2018/7/19 上午9:44
 */
public class MyHystrixCommand extends HystrixCommand<String> {

	protected MyHystrixCommand(String groupKey) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)));
	}

	@Override
	protected String run() throws Exception {
		//实际调用外部的地方
		return "reality invoke。";
	}

	@Override
	protected String getFallback() {
		//run抛出异常，或者调用超时之后调用fallback
		return "invoke failed。";
	}

	public static void main(String args[]){
		MyHystrixCommand hystrixCommand = new MyHystrixCommand("myCommand");
		System.out.println(hystrixCommand.execute());
	}
}
