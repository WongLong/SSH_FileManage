package com.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		User loginedUser = (User) session.get("loginedUser");
		
		if(loginedUser != null)
			return invocation.invoke();
		
		ServletActionContext.getRequest().setAttribute("error", "请登陆后再进行访问！");
		return "login";
	}
}
