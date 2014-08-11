package com.ai.free.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ai.free.dao.impl.BaseDAOImpl;
import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.model.EcoMenu;
import com.ai.free.util.EcLogger;

public class TestMenu {
	private ApplicationContext ctx;
	private Map<String, EcoMenu> parentMenuMap = null;
	private EcoMenu result;
	
	@Before
	public void init(){
		ctx = new FileSystemXmlApplicationContext("E:\\workspace\\springFactory\\src\\main\\resources\\applicationContext.xml");
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
		dao.executeUpdate("delete EcoMenu");
		EcoMenu root = new EcoMenu();
		root.setParentmenuid(0);
		root.setMenuname("基础功能");
		root.setIsused(true);
		root.setState("closed");
		root.setNotes("有子菜单");
		result = dao.create(root);
		createMenuByList(file);
	}
	
	private void createMenuByList(File f) throws Exception{
		IBaseDAO dao = ctx.getBean(BaseDAOImpl.class);
		if(f.isDirectory()){
			EcoMenu m = new EcoMenu();
			m.setParentmenuid(result.getMenuid());
			m.setMenuname(f.getName());
			m.setIsused(true);
			m.setState("closed");
			m.setNotes("有子菜单");
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
				EcoMenu m = new EcoMenu();
				m.setParentmenuid(parentMenuMap.get(parentMenuName).getMenuid());
				m.setMenuname(f.getName());
				m.setIsused(true);
				m.setState("open");
				m.setNotes("无子菜单");
				m.setMenuurl(String.format("/jquery-easyui-1.3.2/demo/%s/%s", kv[kv.length-2], kv[kv.length-1]));
				m = dao.create(m);
			}
		}
	}

}
