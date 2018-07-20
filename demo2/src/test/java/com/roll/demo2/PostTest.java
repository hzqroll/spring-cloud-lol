package com.roll.demo2;

import org.springframework.web.client.RestTemplate;

/**
 * @author haozq
 * Date: 2018/7/17 下午4:46
 */
public class PostTest {
	public static void main(String a[]) {
		/*RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < 10000; i++) {
			String b = restTemplate.getForObject("http://localhost:9002/demo2-server/get-demo1-test", String.class);
			System.out.println(b);
		}*/

		System.out.println(1000%2);
	}
}
