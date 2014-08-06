package com.ai.free.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ai.free.db.ServiceFactory;
import com.ai.free.model.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UpdateAction extends ActionSupport {
	private String result;

	public String update() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		String initid = request.getParameter("initid");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		TUser user = null;
		try {
			user = ServiceFactory.getTUserSV().queryUserByUuid(initid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null || user.getInitid() == null) {
			result = "notexist";
			return "json";
		}

		if (user.getInitid() != null && !user.getPassword().equals(password)) {
			result = "failure";
			return "json";
		}

		if (user.getInitid() != null && user.getPassword().equals(password)) {
			int end = 0;
			try {
				user.setPassword(newpassword);
				end = ServiceFactory.getTUserSV().updateTUserPassword(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (end == 1) {
				result = "success";
				return "json";
			} else {
				result = "updatefailure";
				return "json";
			}

		}
		return null;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
