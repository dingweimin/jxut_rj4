package com.jxut.dao.impl;

import com.jxut.dao.UserDao;
import com.jxut.model.PageUtil;
import com.jxut.model.User;
import com.jxut.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WeiMin
 * @Date: 2022/5/24 11:52
 */
public class UserDaoImpl implements UserDao {
    /*
    登录功能
     */
    @Override
    public User login(User user) {
        User u = null;
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select * from t4_user where username =? and password =?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getPassword());
            rs= psmt.executeQuery();
            while (rs.next()){
                u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setRealName(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setSex(rs.getString(5));
                u.setBirthday(rs.getDate(6));
                u.setTel(rs.getString(7));
                u.setAddress(rs.getString(8));
                u.setType(rs.getString(9));
                u.setPic(rs.getString(10));
                u.setIf_valid(rs.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
/*
获取全部用户
 */
    @Override
    public List<User> getAll(PageUtil page) {
        User u = null;
        List<User> users =new ArrayList<User>();
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select * from t4_user");
            sql.append(" limit ?,?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());

            psmt.setInt(1, (page.getCurrentPage()-1)*page.getPageSize());
            psmt.setInt(2, page.getPageSize());
            rs= psmt.executeQuery();
            while (rs.next()){
                u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setRealName(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setSex(rs.getString(5));
                u.setBirthday(rs.getDate(6));
                u.setTel(rs.getString(7));
                u.setAddress(rs.getString(8));
                u.setType(rs.getString(9));
                u.setPic(rs.getString(10));
                u.setIf_valid(rs.getString(11));
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
/*
添加用户
 */
    @Override
    public boolean addUser(User user) {
        boolean ruselt = false;
        Connection conn = null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("insert into t4_user(username,realname,password,sex,birthday,tel,address,type,pic)");
            sql.append(" values(?,?,?,?,?,?,?,?,?)");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getRealName());
            psmt.setString(3, user.getPassword());
            psmt.setString(4, user.getSex());
            psmt.setDate(5, (Date) user.getBirthday());
            psmt.setString(6, user.getTel());
            psmt.setString(7, user.getAddress());
            psmt.setString(8, user.getType());
            psmt.setString(9, user.getPic());
            psmt.executeUpdate();
    ruselt = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ruselt;
    }
/*
删除用户
 */
    @Override
    public boolean deleteUserByid(int id) {
        boolean ruselt = false;
        Connection conn = null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("delete from t4_user where id =?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            psmt.setInt(1, id);
            psmt.executeUpdate();
            ruselt = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ruselt;
    }


    /*
    根据id查询用户
     */
   @Override
    public User queryUserById(int id) {
        User u = null;
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select * from t4_user where id =?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            psmt.setInt(1, id);
            rs= psmt.executeQuery();
            while (rs.next()){
                u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setRealName(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setSex(rs.getString(5));
                u.setBirthday(rs.getDate(6));
                u.setTel(rs.getString(7));
                u.setAddress(rs.getString(8));
                u.setType(rs.getString(9));
                u.setPic(rs.getString(10));
                u.setIf_valid(rs.getString(11));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public int getAllCount() {
       int count = 0;
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select count(*) from t4_user");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            rs= psmt.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
