package com.roll.demo2.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.roll.demo2.Demo1Service;
import com.roll.demo2.service.RefactorUserService;
import com.roll.poseidon.api.pojo.User;
import rx.Observable;

/**
 * Created by haozq
 * Date: 2018/6/4 下午5:21
 */
@RestController
@RequestMapping("demo2-server")
public class Demo2Controller {
	@Autowired
	private Demo1Service demo1Service;

	@Autowired
	private RefactorUserService refactorUserService;

	@RequestMapping("get-demo1-test")
	@HystrixCommand(fallbackMethod = "fallback", groupKey = "userGroup")
	public String getDemo1Name() throws InterruptedException {
		//测试重试机制
		int sleepTime = new Random().nextInt(300);
		Thread.sleep(sleepTime);
		return demo1Service.getAppName();
	}

	public String fallback(Throwable t) {
		System.out.println("erroe: " + t.getMessage());
		return "error";
	}

	@RequestMapping("get-user")
	@HystrixCollapser(batchMethod = "getAllUser")
	public User getUser(@RequestParam("uid") Long uid) {
		//测试重试机制
		/*int sleepTime = new Random().nextInt(3000);
		Thread.sleep(sleepTime);*/
		return refactorUserService.get(uid);
	}

	public User getAllUser(List<Long> uidList) {
		//测试重试机制
		/*int sleepTime = new Random().nextInt(3000);
		Thread.sleep(sleepTime);*/
		return refactorUserService.get(uidList.get(1));
	}

	//@HystrixCommand
	@RequestMapping("get-user-test")
	public User testSendRequest(@RequestParam("uid") long uid) {
		return refactorUserService.get(uid);
	}

	//@HystrixCommand
	@RequestMapping("get-user-test1")
	public User testSendRequest1(@RequestParam("uid") long uid) {
		Observable<User> observable = Observable.create(subscriber -> {
			try {
				if (!subscriber.isUnsubscribed()) {
					User user = refactorUserService.get(uid);
					subscriber.onNext(user);
					subscriber.onCompleted();
				}
			} catch (Exception e) {
				subscriber.onError(e);
			}
		});
		return refactorUserService.get(uid);
	}

	@RequestMapping("get-user-bycache")
	@CacheResult
	@HystrixCommand(commandKey = "getUserByid", groupKey = "userGroup")
	public User getUserByCache(@RequestParam("uid") /*@CacheKey(value = "uid")*/ Long uid) {
		//测试重试机制
		/*int sleepTime = new Random().nextInt(3000);
		Thread.sleep(sleepTime);*/
		return refactorUserService.get(uid);
	}

	@RequestMapping("remove-user")
	@CacheRemove(commandKey = "getUserByid")
	public User removeUser(@RequestParam("uid") /*@CacheKey(value = "uid")*/ Long uid) {
		//测试重试机制
		/*int sleepTime = new Random().nextInt(3000);
		Thread.sleep(sleepTime);*/
		return refactorUserService.get(uid);
	}
}
