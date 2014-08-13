package com.ai.free.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.free.dao.impl.BaseDAOImpl;
import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.model.EcoMenu;
import com.ai.free.util.EcLogger;

public class TestMenu {
	private ApplicationContext ctx;
	private Map<String, EcoMenu> parentMenuMap = null;
	
	@Before
	public void init(){
		//ctx = new FileSystemXmlApplicationContext("E:\\workspace\\springFactory\\src\\main\\resources\\applicationContext.xml");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() throws Exception {
		parentMenuMap = new HashMap<String, EcoMenu>();
		IBaseDAO dao = ctx.getBean(BaseDAOImpl.class);
		File file = new File("src/main/webapp/jquery-easyui-1.3.2/demo/");
		if(file.isFile()){
			EcLogger.info("file is not a directory");
			return;
		}
		dao.executeUpdate("delete EcoMenu where menuid > 100");
		createMenuByList(file);
	}
	
	private void createMenuByList(File f) throws Exception{
		IBaseDAO dao = ctx.getBean(BaseDAOImpl.class);
		if(f.isDirectory()){
			EcoMenu m = new EcoMenu(f.getName(), 1);
			m = dao.create(m);
			parentMenuMap.put(m.getMenuname(), m);
			File[] fs = f.listFiles();
			for (File file : fs) {
				createMenuByList(file);
			}
		}else{
			String path = f.getPath();
			if(path.endsWith(".html")){
				String[] kv = path.split("\\\\");
				String parentMenuName = kv[kv.length-2];
				EcoMenu m = new EcoMenu(f.getName(), String.format("/jquery-easyui-1.3.2/demo/%s/%s", kv[kv.length-2], kv[kv.length-1]), parentMenuMap.get(parentMenuName).getMenuid());
				m = dao.create(m);
			}
		}
	}

}
