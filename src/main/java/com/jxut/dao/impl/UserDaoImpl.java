package com.jxut.dao.impl;

import com.jxut.dao.UserDao;
import com.jxut.model.PageUtil;
import com.jxut.model.User;
import com.jxut.util.DbUtil;
import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            rs= psmt.executeQuery();//执行查询操作
            while (rs.next()){//遍历结果集
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
    public List<User> getAll(PageUtil page, Map map) {
        User u = null;
        List<User> users =new ArrayList<User>();
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select * from t4_user where 1=1 ");
            if(!StringUtils.isNullOrEmpty((String) map.get("realName"))){//数值不为空时，加入查询条件。判定用户是否输入了查询条件
                sql.append("and realName like ?");//模糊查询
            }
            sql.append(" limit ?,?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            int i =1;//设置动态参数，有查询条件时，第一个参数为查询条件，递增++
            if(!StringUtils.isNullOrEmpty((String) map.get("realName"))){
                psmt.setString(i++,"%"+map.get("realName").toString()+"%");
            }
            psmt.setInt(i++,(page.getCurrentPage()-1)*page.getPageSize());
            psmt.setInt(i, page.getPageSize());
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
    public boolean updateUser(User user) {
        boolean ruselt = false; //返回值
        User u = null;
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("update t4_user set realName=?,sex=?,birthday=?,tel=?,address=?,type=? where id=?");
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            psmt.setString(1,user.getRealName());
            psmt.setString(2,user.getSex());
            psmt.setDate(3,(Date) user.getBirthday());
            psmt.setString(4,user.getTel());
            psmt.setString(5,user.getAddress());
            psmt.setString(6,user.getType());
            psmt.setInt(7 ,user.getId());
            psmt.executeUpdate();
            ruselt = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruselt;
    }

    /*
    计算用户数
     */
    @Override
    public int getAllCount(Map map) {
       int count = 0;
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select count(*) from t4_user where 1=1 ");
            if(!StringUtils.isNullOrEmpty((String) map.get("realName"))){
                sql.append("and realName like ?");
            }
            conn = DbUtil.getConnection();
            psmt =conn.prepareStatement(sql.toString());
            if(!StringUtils.isNullOrEmpty((String) map.get("realName"))){
                psmt.setString(1,"%"+map.get("realName").toString()+"%");
            }
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
