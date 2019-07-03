package com.example.others.presto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrestoJDBC {
   public static void main(String[] args) throws SQLException, ClassNotFoundException {
      Class.forName("com.facebook.presto.jdbc.PrestoDriver");
      Connection connection = DriverManager.getConnection("jdbc:presto://221.228.75.93:9999/hive/default","hive",null);  ;
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("show tables");
      while (rs.next()) {
         System.out.println(rs.getString(1));
      }
      rs.close();
      connection.close();

   }

}
