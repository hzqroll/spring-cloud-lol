package com.roll.demo2;

import java.lang.reflect.Method;

/**
 * @author haozq
 * Date: 2018/7/18 上午9:48
 */
public class InvokeTest1 {

	public String print(String a) {
		System.out.println("get args: " + a);
		return a;
	}

	public static void main(String args[]) {
		InvokeTest1 invokeTest = new InvokeTest1();
		try {
			Object[] arg = new Object[1];
			arg[0] = "12";
			Method m = invokeTest.getClass().getMethod("print", String.class);
			System.out.println(m.invoke(invokeTest, "123"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
