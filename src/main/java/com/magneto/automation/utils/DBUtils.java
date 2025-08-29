package com.magneto.automation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    private static Connection connection;

    public static void connectDB(String url, String user, String password) {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // for MySQL
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ DB connected: " + url);
            }
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed", e);
        }
    }

    // ✅ Add this method
    public static ResultSet runQuery(String sql) {
        try {
            Statement stmt = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException("DB query failed: " + sql, e);
        }
    }

    public static void closeDB() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ DB connection closed");
            }
        } catch (SQLException ignore) {}
    }
}
