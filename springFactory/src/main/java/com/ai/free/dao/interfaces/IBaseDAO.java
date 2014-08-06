package com.ai.free.dao.interfaces;


import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.ai.free.db.Page;

public interface IBaseDAO {

	public abstract <T> T create(T t);

	public abstract <T> T update(T t);

	public abstract <T, PK extends Serializable> void deleteByID(Class<T> type,
			PK id);

	public abstract <T, PK extends Serializable> T findByID(Class<T> type, PK id);

	public abstract <T> List<T> find(String queryString);
	
	public <T> List<T> find(String queryString, Object... value);
	
	@SuppressWarnings("rawtypes")
	public List findByPageFree(final String hql, final int offset, final int pageSize, final Object... values);
	
	public void findByPageFree(final Page page, final String hql);
	
	public void findByPageFree(final Page page, final String hql, final Object value);
	
	public void findByPageFree(final Page page, final String hql, final Object... values);
	
	public <T> void executeUpdate(final String hql) throws DataAccessException;
	
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
