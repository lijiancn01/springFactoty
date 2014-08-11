package com.ai.free.service.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ai.free.db.Page;
import com.ai.free.model.EcoMenu;
import com.ai.free.model.TUser;

public interface ITUserSV {
	public List<TUser> queryUser() throws Exception;
	
	public TUser updateUser(TUser user) throws Exception;
	
	public void queryUserByPage(Page page) throws Exception;
	
	public List<TUser> queryUserListByCriteria(int firstResult, int maxResults) throws Exception;
	
	public TUser queryUserByUuid(String uuid) throws Exception;
	
	public int updateTUserPassword(TUser user) throws Exception;
	
	/**
	 * 
	 * @param user
	 * @return 0失败，1成功
	 * @throws Exception
	 */
	public int addUser(TUser user) throws Exception;
	
	public int delUser(String inits) throws Exception;
	
	public List<EcoMenu> queryChildren(long parentMenuId) throws Exception;
	
	public List<EcoMenu> queryChildrenByCriteria(long parentMenuId) throws Exception;
	
	public <T> List<T> queryByCriteria(DetachedCriteria detachedCriteria, int firstResult, int maxResults) throws Exception;
	
	public String getTUserId() throws Exception;
}
