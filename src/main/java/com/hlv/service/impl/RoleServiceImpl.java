package com.hlv.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hlv.dao.RoleDAO;
import com.hlv.entity.Roles;
import com.hlv.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO RolesDAO;

	@Override
	@Transactional
	public Roles getRolesByRoleName(String roleName) {
		return this.RolesDAO.getRolesByRoleName(roleName);
	}
	
	@Override
	@Transactional
	public Roles getRolesByRoleCode(String roleCode) {
		return this.RolesDAO.getRolesByRoleCode(roleCode);
	}
	
	@Override
	@Transactional
	public List<Roles> getRolesByUserId(Long id) {
		return this.RolesDAO.getRolesByUserId(id);
	}
	
	@Override
	@Transactional
	public List<Roles> getRolesByNotUserId(Long id) {
		return this.RolesDAO.getRolesByNotUserId(id);
	}	
	
	@Override
	@Transactional
	public void addRoles(Roles p) {
		this.RolesDAO.addRoles(p);
	}

	@Override
	@Transactional
	public void updateRoles(Roles p) {
		this.RolesDAO.updateRoles(p);
	}

	@Override
	@Transactional
	public List<Roles> listRoles() {
		return this.RolesDAO.listRoles();
	}

	@Override
	@Transactional
	public Roles getRolesById(Long id) {
		return this.RolesDAO.getRolesById(id);
	}
	
	@Override
	@Transactional
	public List<Roles> findRoleNotByIds(List<Long> id)
	{
		return this.RolesDAO.findNotIds(id);
	}
	
	@Override
	@Transactional
	public List<Roles> findRoleByIds(List<Long> id)
	{
		return this.RolesDAO.findIds(id);
	}
	
	@Override
	@Transactional
	public void removeRoles(Long id) {
		this.RolesDAO.removeRoles(id);
	}

}
