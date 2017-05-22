package com.hlv.service;

import java.util.List;
import com.hlv.bean.UsersBean;
import com.hlv.entity.Users;
 
public interface UserService {
 
	public Users getUserByUsername(String username);
    public Users findId(Long id);
    public void addUser(Users p);
    public void updateUser(Users p);
    public void update(Users p);
    public UsersBean findUsers(UsersBean bean);
    public List<Users> listUsers();
    public void removeUser(Long id);
    public void addListUser(List<Users> lstUsers);
    public List<Users> getUsersByJobId(Long id);
    public List<Users> getUsersByNotJobId(Long id);
    public List<Users> findUsersByIds(List<Long> ids);
     
}