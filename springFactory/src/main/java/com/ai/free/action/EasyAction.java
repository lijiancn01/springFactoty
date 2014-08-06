package com.ai.free.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ai.free.db.ServiceFactory;
import com.ai.free.model.EcoMenu;
import com.ai.free.model.TUser;
import com.ai.free.model.TreeAttrubutes;
import com.ai.free.model.TreeEasyUI;
import com.ai.free.util.EcLogger;
import com.ai.free.util.HttpRequestUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EasyAction extends ActionSupport {
	// 当前页码
	private int page;
	private int total;
	// 征询意见结果集
	private List<?> rows;
	private String result;
	List<TreeEasyUI> treelist;
	private JSONArray treeArray;
	private Map<String, Object> resultMap;
	
	private int pageIndex;
	private int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public JSONArray getTreeArray() {
		return treeArray;
	}

	public void setTreeArray(JSONArray treeArray) {
		this.treeArray = treeArray;
	}

	public List<TreeEasyUI> getTreelist() {
		return treelist;
	}

	public void setTreelist(List<TreeEasyUI> treelist) {
		this.treelist = treelist;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public String queryUserListByCriteria() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取每页显示的行数
		int pageRows = HttpRequestUtil.getAsInt(request, "rows", 10);
		String userName = request.getParameter("userName");
		int offset = (page-1) * pageRows;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TUser.class);
		if(StringUtils.isNotEmpty(userName)){
			detachedCriteria.add(Restrictions.eq("username", userName));
		}
		int count = ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, -1, -1).size();
		List<TUser> resultList = ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, offset, pageRows);
		this.setRows(resultList);
		this.setTotal(count);
		return SUCCESS;
	}
	
	public String queryUserListByCriteria2() throws Exception {
		resultMap = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 获取每页显示的行数
			String userName = request.getParameter("userName");
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TUser.class);
			if(StringUtils.isNotEmpty(userName)){
				detachedCriteria.add(Restrictions.eq("username", userName));
			}
			EcLogger.info(String.format("分页参数：pageIndex=%s, pageSize=%s", pageIndex, pageSize));
			int offset = (pageIndex-1) * pageSize;
			int count = ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, -1, -1).size();
			List<TUser> resultList = ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, offset, pageSize);
			resultMap.put("Flag", "success");
			resultMap.put("total", count);
			resultMap.put("rows", resultList);
		} catch (Exception e) {
			resultMap.put("Flag", "fail");
			resultMap.put("Msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String addUserMap() throws Exception {
		resultMap = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userName = HttpRequestUtil.getAsString(request, "userName");
			String password = HttpRequestUtil.getAsString(request, "password");
			String nickName = HttpRequestUtil.getAsString(request, "nickName");
			TUser user = new TUser();
			user.setUsername(userName);
			user.setPassword(password);
			user.setNickname(nickName);
			user.setBorn(new Date());
			ServiceFactory.getTUserSV().addUser(user);
			resultMap.put("Flag", "success");
		} catch (Exception e) {
			resultMap.put("Flag", "fail");
			resultMap.put("Msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String updateUserMap() throws Exception {
		resultMap = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String uuid = HttpRequestUtil.getAsString(request, "uuid");
			String userName = HttpRequestUtil.getAsString(request, "userName");
			String password = HttpRequestUtil.getAsString(request, "password");
			String nickName = HttpRequestUtil.getAsString(request, "nickName");
			TUser user = ServiceFactory.getTUserSV().queryUserByUuid(uuid);
			user.setUsername(userName);
			user.setPassword(password);
			user.setNickname(nickName);
			ServiceFactory.getTUserSV().updateUser(user);
			resultMap.put("Flag", "success");
		} catch (Exception e) {
			resultMap.put("Flag", "fail");
			resultMap.put("Msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String delUserMap() throws Exception {
		resultMap = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String inits = HttpRequestUtil.getAsString(request, "inits");
			String[] kv = inits.split(",");
			if(kv == null || kv.length == 0){
				throw new Exception("请选择要删除的行");
			}
			String param = "";
			for (String s : kv) {
				param += String.format(",\'%s\'", s);
			}
			param = param.substring(1);
			ServiceFactory.getTUserSV().delUser(param);
			resultMap.put("Flag", "success");
		} catch (Exception e) {
			resultMap.put("Flag", "fail");
			resultMap.put("Msg", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String queryMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		long parentMenuId = HttpRequestUtil.getAsLong(request, "parentMenuId");
		List<EcoMenu> menulist = ServiceFactory.getTUserSV().queryChildrenByCriteria(parentMenuId);
		treelist = new ArrayList<TreeEasyUI>();
		for (EcoMenu menu : menulist) {
			TreeEasyUI ui = new TreeEasyUI();
			ui.setId(menu.getMenuid());
			ui.setText(menu.getMenuname());
			ui.setState(menu.getState());
			ui.setChecked(true);
			ui.setAttributes(new TreeAttrubutes(menu.getMenuurl(), parentMenuId));
			treelist.add(ui);
		}
		treeArray = JSONArray.fromObject(treelist);
		return SUCCESS;
	}
	
	public String queryMenuId() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		long parentMenuId = HttpRequestUtil.getAsLong(request, "id");
		List<EcoMenu> menulist = ServiceFactory.getTUserSV().queryChildrenByCriteria(parentMenuId);
		treelist = new ArrayList<TreeEasyUI>();
		for (EcoMenu menu : menulist) {
			TreeEasyUI ui = new TreeEasyUI();
			ui.setId(menu.getMenuid());
			ui.setText(menu.getMenuname());
			ui.setState(menu.getState());
			ui.setChecked(true);
			ui.setAttributes(new TreeAttrubutes(menu.getMenuurl(), parentMenuId));
			treelist.add(ui);
		}
		treeArray = JSONArray.fromObject(treelist);
		return SUCCESS;
	}
	
	
	

}
