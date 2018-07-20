package com.roll.demo2.command;

/**
 * 请求者角色类
 *
 * @author haozq
 * Date: 2018/7/18 下午5:51
 */
public class Invoker {
	/**
	 * 持有命令对象
	 */
	private Command command;

	/**
	 * 构造方法
	 */
	public Invoker(Command command) {
		this.command = command;
	}

	/**
	 * 行动方法
	 */
	public void action() {
		command.execute();
	}
}
