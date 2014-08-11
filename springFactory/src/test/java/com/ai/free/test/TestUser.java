package com.ai.free.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ai.free.dao.impl.BaseDAOImpl;
import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.model.TUser;

public class TestUser {
	private ApplicationContext ctx;
	
	@Before
	public void init(){
		ctx = new FileSystemXmlApplicationContext("E:\\workspace\\springFactory\\src\\main\\resources\\applicationContext.xml");
	}

	@Test
	public void test() throws Exception {
		IBaseDAO dao = ctx.getBean(BaseDAOImpl.class);
		List<TUser> userlist = dao.find("from TUser");
		System.out.println(userlist.size());
	}

}
