package com.roll.demo1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by haozq
 * Date: 2018/6/15 下午3:30
 */
public class Test {
	public static void main(String args[]) {
		Set<String> a = new HashSet<>();
		a.add("1");
		a.add("1");
		System.out.println(a.toString());
	}
}
