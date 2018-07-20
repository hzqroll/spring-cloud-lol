package com.roll.demo2;

import java.lang.reflect.Method;

/**
 * @author haozq
 * Date: 2018/7/18 上午9:48
 */
public class InvokeTest {

	public String print(String a) {
		System.out.println("get args: " + a);
		return a;
	}

	public static void main(String args[]){
		InvokeTest invokeTest = new InvokeTest();
		try {
			Object arg = new Object[1];
			Method m = invokeTest.getClass().getMethod("print");
			m.invoke(String.class,arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
