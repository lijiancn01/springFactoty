package com.ai.free.action;

import java.util.ArrayList;
import java.util.List;

import com.ai.free.db.ServiceFactory;
import com.ai.free.model.TUser;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TUserAction extends ActionSupport{
	public List<TUser> userlist = new ArrayList<TUser>();
	
	public List<TUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<TUser> userlist) {
		this.userlist = userlist;
	}

	public String queryUser() throws Exception{
		userlist = ServiceFactory.getTUserSV().queryUser();
		System.out.println(userlist.size());
		return SUCCESS;
	}
}
