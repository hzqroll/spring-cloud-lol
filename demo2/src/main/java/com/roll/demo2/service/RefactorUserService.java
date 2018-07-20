package com.roll.demo2.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.roll.poseidon.api.UserService;

/**
 * Created by haozq
 * Date: 2018/6/12 上午10:32
 */
@FeignClient("demo1-server")
public interface RefactorUserService extends UserService {
}
