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
public class ConnectionManager {

    private static ConnectionManager instance;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String M_CONN_STRING = "jdbc:mysql://localhost/california";

    private DBType dbType = DBType.MYSQL;
    private Connection connection = null;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void setDBType(DBType dbType) {
        this.dbType = dbType;
    }

    private boolean openConnection() {
        try {
            switch (dbType) {
                case MYSQL:
                    connection = DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                case HSQLDB:
                    connection = DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
                    return true;
                default:
                    return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {
                return connection;
            } else {
                return null;
            }
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
