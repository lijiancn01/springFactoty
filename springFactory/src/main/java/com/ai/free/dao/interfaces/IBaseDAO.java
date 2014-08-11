package com.ai.free.dao.interfaces;


import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ai.free.db.Page;

public interface IBaseDAO {

	public abstract <T> T create(T t) throws Exception;

	public abstract <T> T update(T t) throws Exception;

	public abstract <T, PK extends Serializable> void deleteByID(Class<T> type,
			PK id) throws Exception;

	public abstract <T, PK extends Serializable> T findByID(Class<T> type, PK id) throws Exception;

	public abstract <T> List<T> find(String queryString) throws Exception;
	
	public <T> List<T> find(String queryString, Object... value) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findByPageFree(final String hql, final int offset, final int pageSize, final Object... values) throws Exception;
	
	public void findByPageFree(final Page page, final String hql) throws Exception;
	
	public void findByPageFree(final Page page, final String hql, final Object value) throws Exception;
	
	public void findByPageFree(final Page page, final String hql, final Object... values) throws Exception;
	
	public <T> void executeUpdate(final String hql) throws Exception;
	
	/**
	 * 查询
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByCriteria(final DetachedCriteria criteria) throws Exception;
	
	/**
	 * 分页查询
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults)
			throws Exception;

}
