package com.roll.starterzuul;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author haozq
 * Date: 2018/7/20 下午3:07
 */
@Service

public class MyFliter extends ZuulFilter {

	/**
	 * {@link ZuulFilter#filterType()} error type.
	 */
	public static final String ERROR_TYPE = "error";

	/**
	 * {@link ZuulFilter#filterType()} post type.
	 */
	public static final String POST_TYPE = "post";

	/**
	 * {@link ZuulFilter#filterType()} pre type.
	 */
	public static final String PRE_TYPE = "pre";

	/**
	 * {@link ZuulFilter#filterType()} route type.
	 */
	public static final String ROUTE_TYPE = "route";

	@Override
	/**
	 * 过滤器类型，他决定过滤器在请求的那个生命周期中执行 以上几种类型
	 */
	public String filterType() {
		return "pre";
	}

	@Override
	/**
	 * 过滤器的执行顺序
	 */
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	/**
	 * 具体处理逻辑
	 */
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		System.out.println(request.toString());

		/*requestContext.setSendZuulResponse(true);
		requestContext.setResponseStatusCode(400);*/

		requestContext.set("error.status_code", HttpServletResponse.SC_GATEWAY_TIMEOUT);
		requestContext.set("error.exception", new RuntimeException());
		return null;
	}
}
