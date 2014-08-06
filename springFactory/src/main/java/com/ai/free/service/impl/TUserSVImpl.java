package com.ai.free.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.db.Page;
import com.ai.free.db.ServiceFactory;
import com.ai.free.model.EcoMenu;
import com.ai.free.model.TUser;
import com.ai.free.service.interfaces.ITUserSV;

public class TUserSVImpl implements ITUserSV {
	public List<TUser> queryUser() throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		return dao.find("from TUser");
	}
	
	public TUser updateUser(TUser user) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		return dao.update(user);
	}
	
	public void queryUserByPage(Page page) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		dao.findByPageFree(page, "from TUser");
	}
	
	public List<TUser> queryUserListByCriteria(int firstResult, int maxResults) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TUser.class);
		return dao.findByCriteria(detachedCriteria, firstResult, maxResults);
	}

	public TUser queryUserByUuid(String uuid) throws Exception {
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		return dao.findByID(TUser.class, uuid);
	}

	public int updateTUserPassword(TUser user) throws Exception {
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		TUser result = dao.update(user);
		return result == null ? 0:1;
	}
	
	public int addUser(TUser user) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		TUser t = dao.create(user);
		return t == null?0:1;
	}
	
	public int delUser(String inits) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		dao.executeUpdate(String.format("delete TUser where initid in(%s)", inits));
		return 1;
	}
	
	public List<EcoMenu> queryChildren(long parentMenuId) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		return dao.find(String.format("from EcoMenu where parentmenuid = %d", parentMenuId));
	}
	
	public List<EcoMenu> queryChildrenByCriteria(long parentMenuId) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EcoMenu.class);
		detachedCriteria.add(Restrictions.eq("parentmenuid", (Long)parentMenuId));
		return dao.findByCriteria(detachedCriteria, -1, -1);
	}
	
	public <T> List<T> queryByCriteria(DetachedCriteria detachedCriteria, int firstResult, int maxResults) throws Exception{
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		return dao.findByCriteria(detachedCriteria, firstResult, maxResults);
	}
	
	public String getTUserId(){
		IBaseDAO dao = ServiceFactory.getBaseDAO();
		TUser user = (TUser)dao.find("select T_USER$seq.nextval as initid from dual").get(0);
		return "S" + user.getInitid();
	}
}
