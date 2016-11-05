/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db;

import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author elsayed
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection(DBType.MYSQL);
            statement = DBUtil.getStatement(connection);
            System.out.println("Connection Suucess");
        } catch (SQLException e) {
            DBUtil.processException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
