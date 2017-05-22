package com.hlv.service;

import java.util.List;
import com.hlv.entity.Roles;
 
public interface RoleService {
 
    public Roles getRolesByRoleName(String roleName);
    public Roles getRolesByRoleCode(String roleCode);
    public List<Roles> getRolesByUserId(Long id);
    public List<Roles> getRolesByNotUserId(Long id);
    public void addRoles(Roles p);
    public void updateRoles(Roles p);
    public List<Roles> listRoles();
    public List<Roles> findRoleNotByIds(List<Long> id);
    public List<Roles> findRoleByIds(List<Long> id);
    public Roles getRolesById(Long id);
    public void removeRoles(Long id);
     
}