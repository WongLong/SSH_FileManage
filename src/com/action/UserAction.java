package com.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user;

	public String login() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		session.clear();
		User loginedUser = userService.login(user);
		if (loginedUser != null) {
			session.put("loginedUser", loginedUser);
			session.put("zNodes", userService.initialization(loginedUser));
			String currentPath = user.getName() + "/";
			session.put("currentPath", currentPath);
			return SUCCESS;
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("error", "用户名或密码错误！");
		return INPUT;
	}
	
	public String getAllUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("userList", userService.queryAll());
		
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return user;
	}
}
