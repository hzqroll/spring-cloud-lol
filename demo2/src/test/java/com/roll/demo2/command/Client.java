package com.roll.demo2.command;

/**
 * @author haozq
 * Date: 2018/7/18 下午5:51
 */
public class Client {
	public static void main(String args[]) {
		Receiver receiver = new Receiver();

		Command command = new ConcreteCommand(receiver);

		Invoker invoker = new Invoker(command);

		invoker.action();
	}
}
