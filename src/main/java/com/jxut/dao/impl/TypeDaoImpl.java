package com.jxut.dao.impl;

import com.jxut.dao.TypeDao;
import com.jxut.model.Type;
import com.jxut.model.User;
import com.jxut.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WeiMin
 * @Date: 2022/5/26 15:13
 */
public class  TypeDaoImpl implements TypeDao {

    @Override
    public List<Type> getType() {
        Type type = null;
        List<Type> users = new ArrayList<Type>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        StringBuffer sql = new StringBuffer();
        try {
            sql.append("select * from t4_type");
            conn = DbUtil.getConnection();
            psmt = conn.prepareStatement(sql.toString());
            rs = psmt.executeQuery();
            while (rs.next()) {
                type = new Type();
                type.setId(rs.getString(1));
                type.setName(rs.getString(2));
                users.add(type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
