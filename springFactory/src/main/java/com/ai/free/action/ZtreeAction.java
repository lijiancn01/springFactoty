package com.ai.free.action;

import java.util.ArrayList;
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
		String id = HttpRequestUtil.getAsString(request, "id", "-1");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EcoMenu.class);
		detachedCriteria.add(Restrictions.eq("parentmenuid", HttpRequestUtil.longValue(id)));
		List<EcoMenu> list = ServiceFactory.getTUserSV().queryByCriteria(detachedCriteria, -1, -1);
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		for (EcoMenu ecoMenu : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ecoMenu.getMenuid());
			map.put("pId", ecoMenu.getParentmenuid());
			map.put("name", ecoMenu.getMenuname());
			String url = ecoMenu.getMenuurl();
			if(StringUtils.isEmpty(url)){
				map.put("isParent", true);
				map.put("hasChild", true);
				map.put("file", "");
			}else{
				map.put("file", ecoMenu.getMenuurl());
				map.put("isParent", false);
				map.put("hasChild", false);
			}
			resultlist.add(map);
		}
		treeArray = JSONArray.fromObject(resultlist);
		return SUCCESS;
	}

}
