package com.example.others.clickhouse;

import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckDataError {

    public static void main(String[] args) throws Exception {

        String date = "2020-03-18";

        List<String> result47 = checkListErrorTables("clickhouse_4_9011", "clickhouse_7_9012", date);
        printError("clichouse4-clickhouse7", "clickhouse4", "clickhouse7", result47);

        List<String> result58 = checkListErrorTables("clickhouse_5_9011", "clickhouse_8_9012", date);
        printError("clichouse5-clickhouse8", "clickhouse5", "clicKhouse8", result58);

        List<String> result69 = checkListErrorTables("clickhouse_6_9011", "clickhouse_9_9012", date);
        printError("clichouse6-clickhouse9", "clickhouse6", "clicKhouse9", result69);

        List<String> result74 = checkListErrorTables("clickhouse_7_9011", "clickhouse_4_9012", date);
        printError("clichouse7-clickhouse4", "clickhouse7", "clicKhouse4", result74);

        List<String> result85 = checkListErrorTables("clickhouse_8_9011", "clickhouse_5_9012", date);
        printError("clichouse8-clickhouse5", "clickhouse8", "clicKhouse5", result85);

        List<String> result96 = checkListErrorTables("clickhouse_9_9011", "clickhouse_6_9012", date);
        printError("clichouse9-clickhouse6", "clickhouse9", "clicKhouse6", result96);

    }

    public static void printError(String title, String shard1, String shard2, List<String> tables) throws Exception {
        System.out.println(title);
        for (String table : tables) {
            try {
                List<String> errorDate = checkDate(table, shard1, shard2);
                System.out.println(table + " : " + StringUtils.join(errorDate, ","));
            } catch (Exception e) {
                System.out.println(table + " :  No Date" );
            }
        }
        System.out.println();
    }


    public static List<String> checkDate(String table, String shard1, String shard2) throws Exception {

        Map<String, Long> shard1Count = new HashMap<>();

        String urlshard1 = "jdbc:clickhouse://" + shard1 + ":8123/temp";
        String sql = "select p_dt,count(1) from dm." + table + " group by p_dt order by p_dt";
        Connection conn = get_conn(urlshard1);
        Statement stmt = conn.createStatement();
        // 创建的表名
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            shard1Count.put(res.getString(1), res.getLong(2));
        }
        conn.close();

        List<String> result = new ArrayList<>();
        String urlshard2 = "jdbc:clickhouse://" + shard2 + ":8124/temp";
        Connection conn2 = get_conn(urlshard2);
        Statement stmt2 = conn2.createStatement();
        // 创建的表名
        ResultSet res2 = stmt2.executeQuery(sql);
        while (res2.next()) {
            String date = res2.getString(1);
            long count = res2.getLong(2);

            long tmp = shard1Count.get(date);

            if (count != tmp) {
                result.add(date);
            }


        }
        conn2.close();

        return result;
    }


    public static List<String> checkListErrorTables(String shard1, String shard2, String date) throws Exception {

        String sql = "select t_table,max(t_count),min(t_count),max(t_count)-min(t_count) from temp.table_count_all where t_host in ('" + shard1 + "','" + shard2 + "' ) and p_dt ='" + date + "'  group by t_table";
        String url = "jdbc:clickhouse://clickhouse4:8123/temp";

        List<String> result = new ArrayList<>();
        Connection conn = get_conn(url);
        Statement stmt = conn.createStatement();
        // 创建的表名
        String tableName = "test";
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            if (res.getLong(4) != 0) {
                result.add(res.getString(1));
            }
        }

        conn.close();
        return result;

    }

    public static Connection get_conn(String url) throws SQLException, ClassNotFoundException {

        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        Connection conn = DriverManager.getConnection(url, "dw_service", "dw_service_818");
        return conn;
    }
}
