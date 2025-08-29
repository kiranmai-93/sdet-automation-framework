package com.magneto.automation.tests;

import com.magneto.automation.utils.DBUtils;

import java.sql.ResultSet;

public class DBTest {
    public static void main(String[] args) throws Exception {
        try {
            // 1) Connect (adjust URL/user/pass)
            DBUtils.connectDB("jdbc:mysql://localhost:3306/testdb", "root", "root");

            // 2) Run query (single row expected for name='Harish')
            ResultSet rs = DBUtils.runQuery("SELECT email FROM users_info WHERE name='Harish'");

            String email = null;
            if (rs.next()) { // move cursor to first row
                email = rs.getString("email");
                System.out.println("DB Email: " + email);

                // 3) Validate
                if ("kinnu@gmail.com".equals(email)) {
                    System.out.println("✅ Email validation passed");
                } else {
                    System.out.println("❌ Email validation failed. Expected: kinnu@gmail.com, Got: " + email);
                }
            } else {
                System.out.println("❌ No rows returned for name='Harish'");
            }

            // If you wanted to print ALL rows (not just Harish), do this instead:
            // ResultSet rsAll = DBUtils.runQuery("SELECT name, email FROM users_info");
            // while (rsAll.next()) {
            //     System.out.println(rsAll.getString("name") + " | " + rsAll.getString("email"));
            // }

        } finally {
            // 4) Close connection
            DBUtils.closeDB();
        }
    }
}
