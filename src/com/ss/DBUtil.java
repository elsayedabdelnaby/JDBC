/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elsayed
 */
public class DBUtil {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String M_CONN_STRING = "jdbc:mysql://localhost/california";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType) {
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
            default:
                return null;
        }
    }

    public static void processException(SQLException e) {
        System.err.println("Error message:" + e.getMessage());
        System.err.println("Error code:" + e.getErrorCode());
        System.err.println("Sql state:" + e.getSQLState());
    }
}
