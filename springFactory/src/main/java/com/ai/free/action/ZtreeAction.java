package com.ai.free.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ai.free.db.ServiceFactory;
import com.ai.free.model.EcoMenu;
import com.ai.free.util.HttpRequestUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ZtreeAction extends ActionSupport {
	private JSONArray treeArray;
	
	public JSONArray getTreeArray() {
		return treeArray;
	}

	public void setTreeArray(JSONArray treeArray) {
		this.treeArray = treeArray;
	}

	public String queryAllMenu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = HttpRequestUtil.getAsString(request, "id");
//		String name = HttpRequestUtil.getAsString(request, "name");
//		String level = HttpRequestUtil.getAsString(request, "level");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EcoMenu.class);
		if(StringUtils.isNotEmpty(id)){
			detachedCriteria.add(Restrictions.eq("parentmenuid", id));
		}
		ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, -1, -1);
		return SUCCESS;
	}

}
