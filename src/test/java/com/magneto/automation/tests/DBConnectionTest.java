package com.magneto.automation.tests;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {


        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/testdb"; // 👈 replace with your DB name
            String user = "root";                              // 👈 replace if different
            String password = "yourpassword";                  // 👈 replace with your password

            try {
                // Load driver (not always needed in new JDBC, but safe)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Connect
                Connection conn = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Connection successful!");

                // Run a test query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users_info");

                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getString("email"));
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


