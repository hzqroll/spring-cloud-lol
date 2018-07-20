package com.roll.demo2.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author haozq
 * Date: 2018/7/17 上午10:42
 */
public class GetStockServiceCommand extends HystrixCommand<String> {

	private RefactorUserService refactorUserService;

	public GetStockServiceCommand(RefactorUserService stockService) {
		super(setter());
		this.refactorUserService = stockService;
	}

	private static Setter setter() {

		//服务分组
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("stock");

		//命令配置
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
				.withFallbackEnabled(true)//默认true
				.withFallbackIsolationSemaphoreMaxConcurrentRequests(100)//默认10
				.withExecutionIsolationThreadInterruptOnFutureCancel(true) //默认false
				.withExecutionIsolationThreadInterruptOnTimeout(true)//默认true
				.withExecutionTimeoutEnabled(true) //默认true
				.withExecutionTimeoutInMilliseconds(1000)//默认1000
				;

		return HystrixCommand.Setter
				.withGroupKey(groupKey)
				.andCommandPropertiesDefaults(commandProperties);

	}


	@Override

	protected String run() throws Exception {
		return "";//可以通过抛出异常，或Thread.sleep模拟超时
	}

	@Override

	protected String getFallback() {//降级方法
		return "有货";
	}
}
