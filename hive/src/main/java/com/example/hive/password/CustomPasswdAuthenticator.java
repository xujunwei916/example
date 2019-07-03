package com.example.hive.password;


import org.apache.hadoop.conf.Configured;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.security.sasl.AuthenticationException;


/**
 * 自定义Hive的认证
 * 验证使用mysql;
 * 在hive中配置hive.jdbc.passwd.auth.url
 */
public class CustomPasswdAuthenticator extends Configured implements PasswdAuthenticationProvider {

    private Logger LOG = LoggerFactory.getLogger(CustomPasswdAuthenticator.class);

    private static final String HIVE_JDBC_PASSWD_AUTH_URL = "hive.jdbc.passwd.auth.url";
    private static final String HIVE_JDBC_PASSWD_AUTH_DRIVER = "hive.jdbc.passwd.auth.driver";
    private static final String HIVE_JDBC_PASSWD_AUTH_USER = "hive.jdbc.passwd.auth.user";
    private static final String HIVE_JDBC_PASSWD_AUTH_PASSWORD = "hive.jdbc.passwd.auth.password";
    private static final String HIVE_JDBC_PASSWD_AUTH_SQL = "select username,password from custom_passwd_authenticator where username = ? and password = ?";

    private static Connection conn = null;


    private static int logCount = 0;


    private Properties initPropeties() {
        Properties prop = new Properties();
        try {
            String url = this.getConf().get(HIVE_JDBC_PASSWD_AUTH_URL);
            String driver = this.getConf().get(HIVE_JDBC_PASSWD_AUTH_DRIVER, "com.mysql.jdbc.Driver");
            String user = this.getConf().get(HIVE_JDBC_PASSWD_AUTH_USER);
            String password = this.getConf().get(HIVE_JDBC_PASSWD_AUTH_PASSWORD);
            Class.forName(driver);

            prop.setProperty("url", url);
            prop.setProperty("user", user);
            prop.setProperty("password", password);


        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("Authenticate Start Up is Error");
            throw new RuntimeException(e.getMessage());

        }
        System.out.println("prop = " + prop);
        return prop;
    }


    @Override
    public void Authenticate(String userName, String passwd)
            throws AuthenticationException {
        LOG.info("user: " + userName + " try login.");
        LOG.info("logCount: " + (++logCount) + " try login.");
        int authenticateResult = getAuthenticatePassword(userName, passwd);
        if (authenticateResult == 0) {
            String message = "validate user and password , user:" + userName;
            LOG.info(message);
            throw new AuthenticationException(message);
        }
        if (authenticateResult == -1) {
            String message = "service validate system is error,Please contact the system administrator or Retry-After,  administrator:" + "xujw@2345.com";
            throw new AuthenticationException(message);
        }
    }

    /**
     * 获取用户认证
     *
     * @param user     用户名
     * @param password 密码
     * @return 认证结果 , -1 认证系统异常，0 认证失败，1认证成功
     */
    private int getAuthenticatePassword(String user, String password) {
        int times = 3;
        int exeSql = execQueryCustomPasswdAuthenticator(user, password);
        while (times > 0 && exeSql == -1) {
            exeSql = execQueryCustomPasswdAuthenticator(user, password);
            times--;
        }
        return exeSql;
    }

    /**
     * 执行查询授权表的用户名密码
     *
     * @param user     用户名
     * @param password 密码
     * @return 是否存在用户名密码
     */
    private int execQueryCustomPasswdAuthenticator(String user, String password) {
        try {
            Connection connection = getConection();
            PreparedStatement ps = connection.prepareStatement(HIVE_JDBC_PASSWD_AUTH_SQL);
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取数据库连接
     */
    private Connection getConection() throws Exception {
        if (conn != null && !conn.isClosed()) {
            return conn;
        } else {
            Properties prop = initPropeties();
            conn = DriverManager.getConnection(prop.getProperty("url"),
                    prop.getProperty("user"), prop.getProperty("password"));
            return conn;
        }
    }


}
