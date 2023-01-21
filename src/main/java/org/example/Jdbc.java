package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {

    private static final String DB_URL = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7588402";
    private static final String DB_USER = "sql7588402";
    private static final String DB_PASSWORD = "3EfRr7bFgl";

    private static Connection connection;
    private static Statement statement;

    private Jdbc() {

    }

    static {
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public static Statement getStatement() {
        if (statement == null){
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return statement;
    }
}
