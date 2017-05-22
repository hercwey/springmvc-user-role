package com.hlv.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.hlv.dao.RoleDAO;
import com.hlv.entity.Roles;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Roles, Long> implements RoleDAO {

	@Override
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public Roles getRolesByRoleName(String roleName) {
		Search search = new Search(Roles.class);
		search.addFilterEqual("name", roleName);
		List<Roles> result = search(search);
		if (result.size() > 0)
			return result.get(0);
		return null;
	}

	@Override
	public Roles getRolesByRoleCode(String roleCode) {
		Search search = new Search(Roles.class);
		search.addFilterEqual("code", roleCode);
		List<Roles> result = search(search);
		if (result.size() > 0)
			return result.get(0);
		return null;
	}

	@Override
	public List<Roles> getRolesByUserId(Long id) {
		String hql = "select role from Users user inner join user.roles role "
				+ "where user.id=:userID";
		Query query = getSession().createQuery(hql);
		query.setParameter("userID", id);
		return query.list();
	}

	@Override
	public List<Roles> getRolesByNotUserId(Long id) {
		String hql = "select r from Roles r where r.id not in "
				+ "(select role.id from Users user inner join user.roles role "
				+ "where user.id=:userID)";
		Query query = getSession().createQuery(hql);
		query.setParameter("userID", id);
		return query.list();
	}
	
	@Override
	public List<Roles> findIds(List<Long> id)
	{
		try
		{
			String hql = "from Roles r where r.id in (:id))";
			Query query = getSession().createQuery(hql);
			query.setParameterList("id", id);
			return query.list();
		}
		catch(Exception ex)
		{
			//System.out.println("ex : " + ex.toString());
			return null;
		}
	}
	
	@Override
	public List<Roles> findNotIds(List<Long> id)
	{
		try
		{
			String hql = "from Roles r where r.id not in (:id))";
			Query query = getSession().createQuery(hql);
			query.setParameterList("id", id);
			return query.list();
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public void addRoles(Roles p) {
		//this.save(p);
		this._persist(p);
	}

	@Override
	public void updateRoles(Roles p) {
		//this.save(p);
		this._update(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listRoles() {
		return findAll();
	}

	@Override
	public Roles getRolesById(Long id) {
		Search search = new Search(Roles.class);
		search.addFilterEqual("id", id);
		List<Roles> result = search(search);
		if (result.size() > 0)
			return result.get(0);
		return null;
	}

	@Override
	public void removeRoles(Long id) {
		Search search = new Search(Roles.class);
		search.addFilterEqual("id", id);
		List<Roles> result = search(search);
		if (result.size() > 0)
			this.remove(result.get(0));
	}

}
