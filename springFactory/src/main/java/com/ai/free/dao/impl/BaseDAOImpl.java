package com.ai.free.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ai.free.dao.interfaces.IBaseDAO;
import com.ai.free.db.Page;
import com.ai.free.util.EcLogger;

public class BaseDAOImpl extends HibernateDaoSupport implements IBaseDAO {
	public <T> T create(T t) {
		getHibernateTemplate().save(t);
		return t;
	}
	
	public <T> T update(T t){
		getHibernateTemplate().update(t);
		return t;
	}
	
	public <T, PK extends Serializable> void deleteByID(Class<T> type, PK id) {
		T t = (T) getHibernateTemplate().get(type, id);
		getHibernateTemplate().delete(t);
	}
	
	public <T, PK extends Serializable> T findByID(Class<T> type, PK id) {
		return (T) getHibernateTemplate().get(type, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String queryString, Object... value) {
		return getHibernateTemplate().find(queryString, value);
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public List findByPageFree(final String hql, final int offset, final int pageSize, final Object... values) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(values != null && values.length > 0){
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				List result = query.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}
	
	public void findByPageFree(final Page page, final String hql){
		findByPageFree(page, hql, (Object[])null);
	}
	
	public void findByPageFree(final Page page, final String hql, final Object value){
		findByPageFree(page, hql, new Object[]{value});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void findByPageFree(final Page page, final String hql, final Object... values) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(values != null && values.length > 0){
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				int total = query.list().size();
				List<?> result = query.setFirstResult(page.getOffset())
						.setMaxResults(page.getPageSize()).list();
				page.setRows(result);
				page.setTotal(total);
				return result;
			}
		});
	}

	public <T> void executeUpdate(final String hql) throws DataAccessException {
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				EcLogger.info(hql);
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(final DetachedCriteria criteria) throws Exception{
		return getHibernateTemplate().findByCriteria(criteria, -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) throws Exception{
		return getHibernateTemplate().findByCriteria(criteria,firstResult,maxResults);
	}

}
