package org.example.database;

import java.sql.*;

public class mySql {
    private static String mysqlURL = "jdbc:mysql://localhost:3306/bt_db";
    private static String mysqlID = "root";
    private static String mysqlPW = "";
    public static Connection connect = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;

    static {
        try {
            connect = DriverManager.getConnection(mysqlURL, mysqlID, mysqlPW);
            if (connect == null) {
                System.out.println("Connect that bai");
            } else {
                System.out.println(" ");
            }
        } catch (SQLException e) {
            System.out.println("Connect failed");
            ;
        }
    }

    public static void test() {
    }

}
