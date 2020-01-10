package com.example.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 简单的jdbc连接hive实例（已开启kerberos服务)
 */

public class HiveSimple2 {
    /**
     * 用于连接Hive所需的一些参数设置 driverName:用于连接hive的JDBC驱动名 When connecting to
     * HiveServer2 with Kerberos authentication, the URL format is:
     * jdbc:hive2://<host>:<port>/<db>;principal=
     * <Server_Principal_of_HiveServer2>
     */
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    // 注意：这里的principal是固定不变的，其指的hive服务所对应的principal,而不是用户所对应的principal
//    private static String url = "jdbc:hive2://cluster137:10000/default";
    private static String url = "jdbc:hive2://cdh-03:10000/default";

    //    private static String url = "jdbc:hive2://master1:10000/default";
    private static ResultSet res;

    public static Connection get_conn() throws SQLException, ClassNotFoundException {

        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, "hadoop", "ddd");
        return conn;
    }

    public static void main(String[] args) {

        try {
            Connection conn = get_conn();
            Statement stmt = conn.createStatement();
            // 创建的表名
            String tableName = "test";
            ResultSet res = stmt.executeQuery("select * from default.aaa where 1=0");
            while (res.next()) {
                System.out.println(res.getString(1));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("!!!!!!END!!!!!!!!");
        }
    }

}