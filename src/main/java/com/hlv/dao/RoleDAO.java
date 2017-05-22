package com.hlv.dao;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.hlv.entity.Roles;

 
public interface RoleDAO extends GenericDAO<Roles, Long> {
 	
    public Roles getRolesByRoleName(String roleName);
    public Roles getRolesByRoleCode(String roleCode);
    public List<Roles> getRolesByUserId(Long id);
    public List<Roles> getRolesByNotUserId(Long id);
    public void addRoles(Roles p);
    public void updateRoles(Roles p);
    public List<Roles> listRoles();
    public List<Roles> findIds(List<Long> id);
	public List<Roles> findNotIds(List<Long> id);
    public Roles getRolesById(Long id);
    public void removeRoles(Long id);

}