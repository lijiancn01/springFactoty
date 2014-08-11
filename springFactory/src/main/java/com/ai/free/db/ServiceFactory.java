package com.ai.free.db;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.service.interfaces.ITUserSV;

public class ServiceFactory {
	public static <T> T getBean(Class<T> entryClass){
		ServletContext servletContext = ServletActionContext.getRequest().getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return ctx.getBean(entryClass);
	}
	
	public static IBaseDAO getBaseDAO(){
		return getBean(IBaseDAO.class);
	}
	
	public static ITUserSV getTUserSV(){
		return getBean(ITUserSV.class);
	}
	
}
