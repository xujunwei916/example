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
    private static String url = "jdbc:hive2://cluster137:10000/default";
    private static String sql = "";
    private static ResultSet res;

    public static Connection get_conn() throws SQLException, ClassNotFoundException {

        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

    /**
     * 查看数据库下所有的表
     *
     * @param statement
     * @return
     */
    public static boolean show_tables(Statement statement) {
        sql = "SHOW TABLES";
        System.out.println("Running:" + sql);
        try {
            ResultSet res = statement.executeQuery(sql);
            System.out.println("执行“+sql+运行结果:");
            while (res.next()) {
                System.out.println("123444");
                System.out.println(res.getString(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取表的描述信息
     *
     * @param statement
     * @param tableName
     * @return
     */
    public static boolean describ_table(Statement statement, String tableName) {
        sql = "DESCRIBE " + tableName;
        try {
            res = statement.executeQuery(sql);
            System.out.print(tableName + "描述信息:");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除表
     *
     * @param statement
     * @param tableName
     * @return
     */
    public static boolean drop_table(Statement statement, String tableName) {
        sql = "DROP TABLE IF EXISTS " + tableName;
        System.out.println("Running:" + sql);
        try {
            statement.execute(sql);
            System.out.println(tableName + "删除成功");
            return true;
        } catch (SQLException e) {
            System.out.println(tableName + "删除失败");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查看表数据
     *
     * @param statement
     * @return
     */
    public static boolean queryData(Statement statement, String tableName) {
        sql = "SELECT * FROM " + tableName + " LIMIT 20";
        System.out.println("Running:" + sql);
        try {
            res = statement.executeQuery(sql);
            System.out.println("执行“+sql+运行结果:");
            while (res.next()) {
                System.out.println(res.getString(1) + "," + res.getString(2) + "," + res.getString(3));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建表
     *
     * @param statement
     * @return
     */
    public static boolean createTable(Statement statement, String tableName) {
        sql = "CREATE TABLE test_1m_test2 AS SELECT * FROM test_1m_test"; //  为了方便直接复制另一张表数据来创建表
        System.out.println("Running:" + sql);
        try {
            boolean execute = statement.execute(sql);
            System.out.println("执行结果 ：" + execute);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        try {
            Connection conn = get_conn();
            Statement stmt = conn.createStatement();
            // 创建的表名
            String tableName = "test";
            ResultSet res =stmt.executeQuery("select `name`,`age` from test");
            while (res
                    .next()){
                System.out.println(res.getString(1)+"\t"+res.getInt(2));
            }

//            show_tables(stmt);
            // describ_table(stmt, tableName);
            /** 删除表 **/
            // drop_table(stmt, tableName);
            // show_tables(stmt);
            // queryData(stmt, tableName);
//            createTable(stmt, tableName);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("!!!!!!END!!!!!!!!");
        }
    }
}