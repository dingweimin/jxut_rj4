package com.jxut.dao;

import com.jxut.model.PageUtil;
import com.jxut.model.User;

import java.util.List;

/**
 * @Author: WeiMin
 * @Date: 2022/5/24 11:50
 */
public interface UserDao {
    public  User login(User user);
    public List<User> getAll(PageUtil page);
    public boolean addUser(User user);
    public boolean deleteUserByid(int id);
    public User queryUserById(int id);
    public int getAllCount();
}