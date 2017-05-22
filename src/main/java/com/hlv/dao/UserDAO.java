package com.hlv.dao;


import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.hlv.entity.Users;

 
public interface UserDAO extends GenericDAO<Users, Long> {
 
    public void addUser(Users p);
    public void updateUser(Users p);
    public List<Users> listUsers();
    public List<Users> getUsersByJobId(Long id);
    public List<Users> getUsersNotByJobId(Long id);
    public List<Users> findUsersByIds(List<Long> ids);

}