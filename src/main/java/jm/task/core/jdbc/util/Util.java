package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mysql  ";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        Connection connection = Util.connection;
        if (connection == null) {
            synchronized(Util.class) {
                connection = Util.connection;
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                        System.out.println("Connected!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Connection error!");
                    }
                }
            }
        }

        return connection;
    }
}