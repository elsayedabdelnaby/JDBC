/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static Statement getStatement(Connection conn) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String SQL) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement;
    }

    public static CallableStatement getCallableStatement(Connection conn, String SQL) throws SQLException {
        return conn.prepareCall(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static void processException(SQLException e) {
        System.err.println("Error message:" + e.getMessage());
        System.err.println("Error code:" + e.getErrorCode());
        System.err.println("Sql state:" + e.getSQLState());
    }
}
