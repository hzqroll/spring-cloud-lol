package com.roll.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haozq
 * Date: 2018/6/2 下午7:55
 */
@RestController
public class Demo1Controller {
	@RequestMapping("get-app-name")
	public String getAppName() {
		return "demo1";
	}
}
