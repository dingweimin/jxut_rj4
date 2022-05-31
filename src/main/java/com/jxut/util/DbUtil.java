package com.jxut.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @Author: WeiMin
 * @Date: 2022/5/11 15:20
 */
public class DbUtil {
    private static final String  DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String  URL = "jdbc:mysql://localhost:3306/shixun?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String  USERNAME = "root";
    private static final String  PASSWORD = "root";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
     return conn;
    }
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null){
                rs.close();
            } if (stmt != null){
                stmt.close();
            } if (conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.println(getConnection());
    }
}
