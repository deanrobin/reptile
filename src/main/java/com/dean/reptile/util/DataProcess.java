package com.dean.reptile.util;

import com.dean.reptile.bean.Accessories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017/8/16.
 */
public class DataProcess {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dota?serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";
    private static DataProcess instance = null;
    static Connection conn = null;
    static Statement stmt = null;

    private DataProcess() {

    }
    public static DataProcess getInstance() {
        if (instance == null) {
            synchronized (DataProcess.class) {
                if (instance == null) {
                    instance = new DataProcess();
                    connect();
                }
            }
        }
        return instance;
    }

    public static void connect () {
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean insert(Accessories ins) {
        String sql = "insert into accessories(name, hero, price, dealPrice, queryTime, dealTime, huzuId) values(?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ins.getName());
            ps.setString(2, ins.getHero());
            ps.setDouble(3, ins.getPrice());
            ps.setDouble(4, ins.getDealPrice());
            ps.setString(5, ins.getQueryTime());
            ps.setString(6, ins.getDealTime());
            ps.setInt(7, ins.getHuzuId());
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean insert404(int i) {
        String sql = "insert into notexist(queryTime, huzuId) values(?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TimeConversionTool.unixTimeToString(System.currentTimeMillis()));
            ps.setInt(2, i);
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean insertError(int i) {
        String sql = "insert into error(queryTime, huzuId) values(?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TimeConversionTool.unixTimeToString(System.currentTimeMillis()));
            ps.setInt(2, i);
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static List<Accessories> query(String sql) {
        System.out.println("sql:" + sql);
        ResultSet rs= null;
        List<Accessories> list =  new ArrayList<>();
        try {
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Accessories ac = new Accessories();
                ac.setId(rs.getInt("id"));
                ac.setHero(rs.getString("hero"));
                ac.setName(rs.getString("name"));
                ac.setPrice(rs.getDouble("price"));
                ac.setDealPrice(rs.getDouble("dealPrice"));
                ac.setQueryTime(rs.getString("queryTime"));
                ac.setDealTime(rs.getString("dealTime"));
                ac.setHuzuId(rs.getInt("huzuId"));
                list.add(ac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
