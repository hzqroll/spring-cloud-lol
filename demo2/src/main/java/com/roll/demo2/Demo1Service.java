package com.roll.demo2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haozq
 * Date: 2018/6/4 下午5:17
 */
@FeignClient("demo1-server")
public interface Demo1Service {
	@RequestMapping("get-app-name")
	String getAppName();
}
