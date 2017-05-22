package com.hlv.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.hlv.dao.UserDAO;
import com.hlv.entity.Users;

@Repository
public class UserDAOImpl extends GenericDAOImpl<Users, Long> implements UserDAO {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void addUser(Users p) {
		this._persist(p);
	}

	@Override
	public void updateUser(Users p) {
		this._update(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> listUsers() {
		return findAll();
	}
	
    public List<Users> getUsersByJobId(Long id) {
    	String hql = "select user from JobInfo info inner join info.users user "
				+ "where info.id=:jobId";
		Query query = getSession().createQuery(hql);
		query.setParameter("jobId", id);
		return query.list();
    }
    public List<Users> getUsersNotByJobId(Long id) {
    	String hql = "select r from Users r where r.id not in "
				+ "(select usr.id from JobInfo info inner join info.users usr "
				+ "where info.id=:jobId)";
		Query query = getSession().createQuery(hql);
		query.setParameter("jobId", id);
		return query.list();
    }
    
    public List<Users> findUsersByIds(List<Long> ids) {
		String hql = "from Users u where u.id in (:id))";
		Query query = getSession().createQuery(hql);
		query.setParameterList("id", ids);
		return query.list();
    }
	
}
