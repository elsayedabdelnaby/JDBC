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
            ResultSet result = statement.executeQuery("SELECT * FROM Tours");
            /*
            Use Cursor To Move first, last and sepcific row
            */
            result.first();         // move to first row
            result.last();          // move to last row
            result.absolute(10);    // move to row number 10
            
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
